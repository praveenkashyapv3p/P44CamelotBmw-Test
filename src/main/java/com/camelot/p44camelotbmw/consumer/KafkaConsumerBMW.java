package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.bmwJsonMapper.CreateShipmentMapper;
import com.camelot.p44camelotbmw.bmwJsonMapper.ShipmentIdMapper;
import com.camelot.p44camelotbmw.constants.CarrierMapping;
import com.camelot.p44camelotbmw.db.CreateShipment;
import com.camelot.p44camelotbmw.db.CreateShipmentRepository;
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

import java.util.Map;

@Service
public class KafkaConsumerBMW {
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    @Autowired
    CreateShipmentRepository createShipmentRepository;
    
    /*Development Consumer*/
    //@KafkaListener(topics = "BMWPushLocal", groupId = "BMWPushLocalGroup")
    /*Production Consumer*/
    @KafkaListener(topics = "bmwPush", groupId = "BMWPushGroup")
    public void getBMWMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        CarrierMapping carrierMapping = new CarrierMapping();
        String bmwShipmentId = "", containerID = "", transportationNetwork = "", carrierID = "", carrierP44ID = "", carrierName = "",
                senderId = "", senderName = "", recipientID = "", recipientName = "", recipientUnloadingPoint = "", planPickUpDate = "",
                planDeliveryDate = "", totalWeightKGS = "", totalVolumeCBM = "";
        try {
            
            JsonObject inputJSON = (JsonObject) JsonParser.parseString(message);
            JsonElement identifiers = inputJSON.getAsJsonObject().get("identifiers");
            containerID = identifiers.getAsJsonObject().get("containerID").getAsString();
            bmwShipmentId = identifiers.getAsJsonObject().get("shipmentID").getAsString();
            transportationNetwork = inputJSON.getAsJsonObject().get("transportationNetwork").getAsString();
            JsonArray contactInfo = (JsonArray) inputJSON.getAsJsonObject().get("contactInformation");
            for (JsonElement contact : contactInfo) {
                senderId = contact.getAsJsonObject().get("sender").getAsJsonObject().get("senderID").getAsString();
                senderName = contact.getAsJsonObject().get("sender").getAsJsonObject().get("senderName").getAsString();
                recipientID = contact.getAsJsonObject().get("recipient").getAsJsonObject().get("recipientID").getAsString();
                recipientName = contact.getAsJsonObject().get("recipient").getAsJsonObject().get("recipientName").getAsString();
                recipientUnloadingPoint = contact.getAsJsonObject().get("recipient").getAsJsonObject().get("recipientUnloadingPoint").getAsString();
                carrierID = contact.getAsJsonObject().get("carrier").getAsJsonObject().get("carrierID").getAsString();
                Map<String, String> stCod = carrierMapping.getCarrierId();
                carrierP44ID = stCod.get(carrierID);
                carrierName = contact.getAsJsonObject().get("carrier").getAsJsonObject().get("carrierName").getAsString();
            }
            JsonElement deliveryInformations = inputJSON.getAsJsonObject().get("deliveryInformations");
            planPickUpDate = deliveryInformations.getAsJsonObject().get("planPickUpDate").getAsString();
            planDeliveryDate = deliveryInformations.getAsJsonObject().get("planDeliveryDate").getAsString();
            JsonElement containerDimensions = inputJSON.getAsJsonObject().get("containerDimensions");
            totalWeightKGS = containerDimensions.getAsJsonObject().get("totalWeightKGS").getAsString();
            totalVolumeCBM = containerDimensions.getAsJsonObject().get("totalVolumeCBM").getAsString();
            JsonArray materials = (JsonArray) inputJSON.getAsJsonObject().get("materials");
            String matrialsString = materials.toString();
            
            CreateShipmentMapper createShipmentMapper = new CreateShipmentMapper();
            CreateShipmentP44 createShipmentP44 = new CreateShipmentP44();
            createShipmentP44 = createShipmentMapper.mapCreateShipment(createShipmentP44, containerID, bmwShipmentId, senderId, senderName,
                    recipientID, recipientName, recipientUnloadingPoint, carrierP44ID, planPickUpDate,
                    planDeliveryDate, totalWeightKGS, totalVolumeCBM);
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
            
            createShipmentRepository.save(new CreateShipment(masterShipmentId, containerID, bmwShipmentId, transportationNetwork, senderId, senderName,
                    recipientID, recipientName, recipientUnloadingPoint, carrierID, carrierP44ID, carrierName, planPickUpDate,
                    planDeliveryDate, totalWeightKGS, totalVolumeCBM, matrialsString));
            
            
            ShipmentIdMapper shipmentIdMapper = new ShipmentIdMapper();
            shipmentIdMapper.getShipmentId(masterShipmentId, bmwShipmentId, carrierP44ID, matrialsString);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}