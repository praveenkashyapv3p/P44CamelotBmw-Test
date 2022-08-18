package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.p44JsonMapper.*;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerP44 {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerP44.class);
    private final KafkaProducer producer;
    @Autowired
    CreateShipmentRepository createShipmentRepository;
    @Autowired
    private RestTemplate restTemplate;
    
    public KafkaConsumerP44(KafkaProducer producer) {
        this.producer = producer;
    }
    
    /*Development Consumer*/
    //@KafkaListener(topics = "p44DataLocal", groupId = "p44DataLocalGroup")
    /*Production Consumer*/
    @KafkaListener(topics = "p44Data", groupId = "bmwGroup")
    public void getP44Message(String message) {
        try {
            BMWMapping bmwMapping = new BMWMapping();
            IdentifiersMapper identifiersMapping = new IdentifiersMapper();
            ContactInformationMapper contactInformationMapping = new ContactInformationMapper();
            CurrentLocationInfoMapper currentLocationInfoMapper = new CurrentLocationInfoMapper();
            TransportLegMapper transportLegMapper = new TransportLegMapper();
            TechnicalDetailsMapper technicalDetailsMapper = new TechnicalDetailsMapper();
            DeliveryInformationMapper deliveryInformationMapper = new DeliveryInformationMapper();
            ContainerDimensionsMapper containerDimensionsMapper = new ContainerDimensionsMapper();
            MaterialMapper materialMapper = new MaterialMapper();
            String jsonKey = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
            String jsonStartingString = "{\"records\":[{\"key\":";
            String jsonStringValue = ",\"value\":";
            String jsonEndString = "}]}";
        
            JsonObject shipment = (JsonObject) JsonParser.parseString(message);
            identifiersMapping.mapIdentifiers(createShipmentRepository, shipment, bmwMapping);
            contactInformationMapping.mapContactInfo(createShipmentRepository, shipment, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(shipment, bmwMapping);
            bmwMapping.setTransportationNetwork("SHIP");
            bmwMapping.setMainTransportMode("SEA");
            transportLegMapper.mapTransportLegInfos(shipment, bmwMapping);
            containerDimensionsMapper.mapContainerDimensions(createShipmentRepository, shipment, bmwMapping);
            materialMapper.mapMaterial(createShipmentRepository, shipment, bmwMapping);
            technicalDetailsMapper.mapTechnicalDetails(jsonKey, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(shipment, bmwMapping);
            String bmwJson = jsonStartingString + jsonKey + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
            this.producer.writeBMWMessage(jsonKey, bmwJson);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(bmwJson, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://p44-tracking-data-int.bmwgroup.com", entity, String.class);
            //System.out.println("response: " + response + "\n" + bmwJson);
        } catch (Exception e) {
            logger.error("Mapping failure " + e);
        }
    }
    
}