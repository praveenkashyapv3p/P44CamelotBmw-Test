package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
import com.camelot.p44camelotbmw.db.RecipientPlantCodeRepository;
import com.camelot.p44camelotbmw.db.SenderPlantCodeRepository;
import com.camelot.p44camelotbmw.entity.toBmwEntity.BMWMapping;
import com.camelot.p44camelotbmw.p44JsonMapper.*;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.util.Arrays;

@Service
public class KafkaConsumerP44 {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerP44.class);
    private final KafkaProducer producer;
    @Autowired
    CreateShipmentRepository createShipmentRepository;
    
    @Autowired
    SenderPlantCodeRepository senderPlantCodeRepository;
    
    @Autowired
    RecipientPlantCodeRepository recipientPlantCodeRepository;
    @Autowired
    private RestTemplate restTemplate;
    
    public KafkaConsumerP44(KafkaProducer producer) {
        this.producer = producer;
    }
    
    /*Development Consumer*/
    @KafkaListener(topics = "p44DataLocal", groupId = "p44DataLocalGroup")
    /*Production Consumer*/
    //@KafkaListener(topics = "p44Data", groupId = "bmwGroup")
    public void getP44Message(String message) {
        BMWMapping bmwMapping = new BMWMapping();
        try {
    
            IdentifiersMapper identifiersMapping = new IdentifiersMapper(producer);
            ContactInformationMapper contactInformationMapping = new ContactInformationMapper();
            CurrentLocationInfoMapper currentLocationInfoMapper = new CurrentLocationInfoMapper();
            TransportLegMapper transportLegMapper = new TransportLegMapper();
            DeliveryInformationMapper deliveryInformationMapper = new DeliveryInformationMapper();
            ContainerDimensionsMapper containerDimensionsMapper = new ContainerDimensionsMapper();
            MaterialMapper materialMapper = new MaterialMapper();
            String jsonKey = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
            String jsonStartingString = "{\"records\":[{\"key\":";
            String jsonStringValue = ",\"value\":";
            String jsonEndString = "}]}";
    
            JsonObject shipment = (JsonObject) JsonParser.parseString(message);
            identifiersMapping.mapIdentifiers(createShipmentRepository, senderPlantCodeRepository, recipientPlantCodeRepository, jsonKey, shipment, bmwMapping);
            contactInformationMapping.mapContactInfo(createShipmentRepository, shipment, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(shipment, bmwMapping);
            bmwMapping.setTransportationNetwork("SHIP");
            bmwMapping.setMainTransportMode("SEA");
            transportLegMapper.mapTransportLegInfos(shipment, bmwMapping);
            containerDimensionsMapper.mapContainerDimensions(createShipmentRepository, shipment, bmwMapping);
            materialMapper.mapMaterial(createShipmentRepository, shipment, bmwMapping);
            bmwMapping.setLifecycleStatus("");
    
            // technicalDetailsMapper.mapTechnicalDetails(jsonKey, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(createShipmentRepository, shipment, bmwMapping);
    
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
                    .create();
            //System.out.println(gson.toJson(bmwMapping));
            String bmwJson = jsonStartingString + jsonKey + jsonStringValue + gson.toJson(bmwMapping) + jsonEndString;
            String containerID = bmwMapping.getIdentifiers().getContainerID();
            /*Temporary tracing of containers for Data validation*/
            if ((Arrays.asList(
                    "GAOU6076128", "BSIU9639751", "TRHU5337518", "MRSU3849066", "MRKU3692924", "TRHU7195840", "EGHU8487330",
                    "MRSU4700966", "UETU5615695", "SUDU5493296", "MSKU0166189", "MSDU8759550", "MSMU6905667", "DRYU9607220",
                    "BEAU5422048", "CBHU8932857", "TCKU6677710", "FCIU9157330", "HLBU3174042"
            )).contains(containerID)) {
                this.producer.writeLogMessage(jsonKey, bmwJson);
            }
            /*Delete above code after Temporary tracing of containers for Data validation is complete*/
    
            this.producer.writeBMWMessage(jsonKey, bmwJson);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(bmwJson, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://p44-tracking-data-dev.bmwgroup.com", entity, String.class);
            //System.out.println("response: " + /*response + "\n" +*/ bmwJson);
        } catch (Exception e) {
            logger.error("Mapping failure " + e + "\n" + message + "\n" + new Gson().toJson(bmwMapping));
        }
    }
}