package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.bmwJsonMapper.CreateShipmentMapper;
import com.camelot.p44camelotbmw.bmwJsonMapper.ShipmentIdMapper;
import com.camelot.p44camelotbmw.constants.CarrierMapping;
import com.camelot.p44camelotbmw.db.*;
import com.camelot.p44camelotbmw.entity.createShipmentEntity.CreateShipmentP44;
import com.google.gson.*;
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

import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumerBMW {
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    @Autowired
    CreateShipmentRepository createShipmentRepository;
    
    @Autowired
    SenderPlantCodeRepository senderPlantCodeRepository;
    
    @Autowired
    RecipientPlantCodeRepository recipientPlantCodeRepository;
    
    /*Development Consumer*/
    //@KafkaListener(topics = "BMWPushLocal", groupId = "BMWPushLocalGroup")
    /*Production Consumer*/
    @KafkaListener(topics = "bmwPush", groupId = "BMWPushGroup")
    public void getBMWMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        CarrierMapping carrierMapping = new CarrierMapping();
        String bmwShipmentId = "", containerID = "", transportationNetwork = "", carrierID = "", carrierP44ID = "", carrierName = "", senderId = "", senderName = "", recipientID = "", recipientName = "", planPickUpDate = "", planDeliveryDate = "", totalWeightKGS = "", totalVolumeCBM = "", bookingNumber = "", billOfLading = "";
        List<SenderPlantCode> senderPlantCodeList;
        List<RecipientPlantCode> recipientPlantCodeList;
        try {
            
            JsonObject inputJSON = (JsonObject) JsonParser.parseString(message);
            JsonElement identifiers = inputJSON.getAsJsonObject().get("identifier");
            containerID = identifiers.getAsJsonObject().get("containerId").getAsString();
            bmwShipmentId = identifiers.getAsJsonObject().get("shipmentId").getAsString();
            bookingNumber = identifiers.getAsJsonObject().get("bookingNumber").getAsString();
            billOfLading = identifiers.getAsJsonObject().get("billOfLading").getAsString();
            transportationNetwork = inputJSON.getAsJsonObject().get("transportationNetwork").getAsString();
            JsonElement contactInfo = inputJSON.getAsJsonObject().get("contactInformation");
            
            senderId = contactInfo.getAsJsonObject().get("sender").getAsJsonObject().get("senderId").getAsString();
            senderName = contactInfo.getAsJsonObject().get("sender").getAsJsonObject().get("senderName").getAsString();
            senderPlantCodeList = senderPlantCodeRepository.findByPackingPlantCodeAndPackingPlantName(senderId, senderName);
            for (SenderPlantCode senderPlantCodeFromDB : senderPlantCodeList) {
                senderName = senderPlantCodeFromDB.getP44DisplayPackingPlant();
            }
            recipientID = contactInfo.getAsJsonObject().get("recipient").getAsJsonObject().get("recipientId").getAsString();
            recipientName = contactInfo.getAsJsonObject().get("recipient").getAsJsonObject().get("recipientName").getAsString();
            recipientPlantCodeList = recipientPlantCodeRepository.findByPlantCodeAndPlantDescription(recipientID, recipientName);
            for (RecipientPlantCode recipientPlantCodeFromDB : recipientPlantCodeList) {
                recipientName = recipientPlantCodeFromDB.getP44DisplayPlant();
            }
            carrierID = contactInfo.getAsJsonObject().get("carrier").getAsJsonObject().get("carrierId").getAsString();
            Map<String, String> stCod = carrierMapping.getCarrierId();
            carrierP44ID = stCod.get(carrierID);
            carrierName = contactInfo.getAsJsonObject().get("carrier").getAsJsonObject().get("carrierName").getAsString();
            
            
            JsonElement deliveryInformation = inputJSON.getAsJsonObject().get("deliveryInformation");
            planPickUpDate = deliveryInformation.getAsJsonObject().get("planPickUpDate").getAsString();
            planDeliveryDate = deliveryInformation.getAsJsonObject().get("planDeliveryDate").getAsString();
            JsonElement containerDimensions = inputJSON.getAsJsonObject().get("containerDimension");
            totalWeightKGS = containerDimensions.getAsJsonObject().get("totalWeight").getAsString();
            totalVolumeCBM = containerDimensions.getAsJsonObject().get("totalVolume").getAsString();
            JsonArray materials = (JsonArray) inputJSON.getAsJsonObject().get("materials");
            String materialsString = materials.toString();
            
            CreateShipmentMapper createShipmentMapper = new CreateShipmentMapper();
            CreateShipmentP44 createShipmentP44 = new CreateShipmentP44();
            createShipmentP44 = createShipmentMapper.mapCreateShipment(createShipmentP44, containerID, bmwShipmentId, bookingNumber, billOfLading, senderId, senderName, recipientID, recipientName, carrierP44ID, planPickUpDate, planDeliveryDate, totalWeightKGS, totalVolumeCBM);
            Gson gson = new Gson();
            String requestBody = gson.toJson(createShipmentP44);
            System.out.println(requestBody);
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://na12.api.project44.com/api/v4/shipments/tracking", entity, String.class);
            String jsonResponse = response.getBody();
            
            JsonObject shipmentIdJSON = (JsonObject) JsonParser.parseString(jsonResponse);
            String masterShipmentId = shipmentIdJSON.get("id").getAsString();
            
            createShipmentRepository.save(new CreateShipment(masterShipmentId, containerID, bmwShipmentId, bookingNumber, billOfLading, transportationNetwork, senderId, senderName, recipientID, recipientName, carrierID, carrierP44ID, carrierName, planPickUpDate, planDeliveryDate, totalWeightKGS, totalVolumeCBM, materialsString));
            
            
            ShipmentIdMapper shipmentIdMapper = new ShipmentIdMapper();
            shipmentIdMapper.getShipmentId(masterShipmentId, bmwShipmentId, carrierP44ID, materialsString);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}