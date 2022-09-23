package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.entity.toBmwEntity.P44ToBmw;
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

import java.util.Arrays;

@Service
public class KafkaConsumerP44 {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerP44.class);
    private final KafkaProducer producer;
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
        P44ToBmw bmwMapping = new P44ToBmw();
        try {
            
            RecipientMapper recipientMapper = new RecipientMapper();
            SenderMapper senderMapper = new SenderMapper();
            CarrierMapper carrierMapper = new CarrierMapper();
            CurrentLocationInfoMapper currentLocationInfoMapper = new CurrentLocationInfoMapper();
            DeliveryInformationMapper deliveryInformationMapper = new DeliveryInformationMapper();
            IdentifiersMapper identifiersMapper = new IdentifiersMapper(producer);
            TransportLegInfoMapper transportLegInfoMapper = new TransportLegInfoMapper();
            ContainerDimensionsMapper containerDimensionsMapper = new ContainerDimensionsMapper();
            MaterialMapper materialMapper = new MaterialMapper();
            
            String jsonKey = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
            String jsonStartingString = "{\"records\":[{\"key\":";
            String jsonStringValue = ",\"value\":";
            String jsonEndString = "}]}";
            
            bmwMapping.setLifecycleStatus("");
            bmwMapping.setLifecycleStatusVerbose("");
            bmwMapping.setMainTransportMode("SEA");
            bmwMapping.setTransportationNetwork("SHIP");
            bmwMapping.setLeadTime("");
            bmwMapping.setCurrentLeadTimePerLeg("");
            bmwMapping.setCurrentLeadTimePickUpUntilCurrentTimestamp("");
            bmwMapping.setCurrentLeadTimePickUpUntilDelivery("");
            bmwMapping.setCurrentLeadTimePickUpUntilEta("");
            
            JsonObject shipment = (JsonObject) JsonParser.parseString(message);
            
            recipientMapper.mapRecipient(shipment, bmwMapping);
            senderMapper.mapSender(shipment, bmwMapping);
            carrierMapper.mapCarrier(shipment, bmwMapping);
            currentLocationInfoMapper.mapCurrLocInfo(shipment, bmwMapping);
            deliveryInformationMapper.mapDeliveryInformation(shipment, bmwMapping);
            identifiersMapper.mapIdentifier(jsonKey, shipment, bmwMapping);
            transportLegInfoMapper.mapTransportLegInfo(shipment, bmwMapping);
            containerDimensionsMapper.mapContainerDimension(shipment, bmwMapping);
            materialMapper.mapMaterial(shipment, bmwMapping);
            
            String bmwJson = jsonStartingString + jsonKey + jsonStringValue + new Gson().toJson(bmwMapping) + jsonEndString;
            String containerID = bmwMapping.getIdentifier().getContainerId();
            
            /*Temporary tracing of containers for Data validation*/
            if ((Arrays.asList("CAIU7053452", "CMAU7681240", "FSCU8704094", "CSNU6428681", "MSKU8538003", "TLLU6848274", "EITU9074179",
                    "TGBU8621308", "HASU4670368", "MSKU9718864", "FANU1740651", "TEMU1608650", "KOCU4488754", "MSKU6889462",
                    "MRSU3202341", "FCIU7369829", "FFAU3068178", "TCNU7686182", "NYKU5107789", "CAIU9003698", "OOCU7340261"))
                    .contains(containerID)) {
                this.producer.writeLogMessage(jsonKey, bmwJson);
            }
            /*Delete above code after Temporary tracing of containers for Data validation is complete*/
            
            this.producer.writeBMWMessage(jsonKey, bmwJson);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(bmwJson, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://p44-tracking-data-int.bmwgroup.com", entity, String.class);
            System.out.println("response: " + response + "\n" + bmwJson);
        } catch (Exception e) {
            logger.error("Mapping failure " + e + "\n" + message + "\n" + new Gson().toJson(bmwMapping));
        }
    }
}