package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.bmwJsonMapper.CreateShipmentMapper;
import com.camelot.p44camelotbmw.bmwJsonMapper.ShipmentIdMapper;
import com.camelot.p44camelotbmw.constants.CarrierMapping;
import com.camelot.p44camelotbmw.entity.createShipmentEntity.CreateShipmentP44;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    
    @KafkaListener(topics = "bmwPush", groupId = "BMWPushGroup")
    public void getBMWMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        CarrierMapping carrierMapping = new CarrierMapping();
        String bmwShipmentId = "", containerID = "", transportationNetwork = "", carrierID = "", carrierP44ID = "", carrierName = "",
                senderId = "", senderName = "", recipientID = "", recipientName = "", recipientUnloadingPoint = "", planPickUpDate = "", planDeliveryDate = "",
                totalWeight = "", totalWeightUnit = "", totalVolume = "", totalVolumeUnit = "", bookingNumber = "", billOfLading = "",
                bmwBusinessRelation = "";
    
        try {
            JsonObject inputJSON = (JsonObject) JsonParser.parseString(message);
            /*
             * transportationNetwork(Not mapped. Fixed value SHIP is returned to BMW)
             */
            transportationNetwork = inputJSON.getAsJsonObject().get("transportationNetwork").getAsString();
        
            /*
             * recipient
             * UnloadingPoint is ignored for now
             */
            JsonElement recipient = inputJSON.getAsJsonObject().get("recipient");
            recipientID = recipient.getAsJsonObject().get("id").getAsString();
            recipientName = recipient.getAsJsonObject().get("name").getAsString();
            recipientUnloadingPoint = recipient.getAsJsonObject().get("unloadingPoint").getAsString();
        
            /*
             * Sender
             */
            JsonElement sender = inputJSON.getAsJsonObject().get("sender");
            senderId = sender.getAsJsonObject().get("id").getAsString();
            senderName = sender.getAsJsonObject().get("name").getAsString();
        
            /*
             * carrier
             */
            JsonElement carrier = inputJSON.getAsJsonObject().get("carrier");
            carrierID = carrier.getAsJsonObject().get("id").getAsString();
            Map<String, String> stCod = carrierMapping.getCarrierId();
            carrierP44ID = stCod.get(carrierID);
            carrierName = carrier.getAsJsonObject().get("name").getAsString();
        
            /*
             * deliveryInformation
             */
            JsonElement deliveryInformation = inputJSON.getAsJsonObject().get("deliveryInformation");
            planPickUpDate = deliveryInformation.getAsJsonObject().get("planPickUpDate").getAsString();
            planDeliveryDate = deliveryInformation.getAsJsonObject().get("planDeliveryDate").getAsString();
        
            /*
             * identifier
             */
            JsonElement identifiers = inputJSON.getAsJsonObject().get("identifier");
            containerID = identifiers.getAsJsonObject().get("containerId").getAsString();
            billOfLading = identifiers.getAsJsonObject().get("billOfLading").getAsString();
            bmwShipmentId = identifiers.getAsJsonObject().get("bmwShipmentId").getAsString();
            bookingNumber = identifiers.getAsJsonObject().get("bookingNumber").getAsString();
            bmwBusinessRelation = identifiers.getAsJsonObject().get("bmwBusinessRelation").getAsString();
        
            /*
             * containerDimension
             * totalVolumeUnit and totalWeightUnit are ignored for now
             */
            JsonElement containerDimensions = inputJSON.getAsJsonObject().get("containerDimension");
            totalVolume = containerDimensions.getAsJsonObject().get("totalVolume").getAsString();
            totalVolumeUnit = containerDimensions.getAsJsonObject().get("totalVolumeUnit").getAsString();
            totalWeight = containerDimensions.getAsJsonObject().get("totalWeight").getAsString();
            totalWeightUnit = containerDimensions.getAsJsonObject().get("totalWeightUnit").getAsString();
        
            /*
             * materials
             */
            JsonArray materials = (JsonArray) inputJSON.getAsJsonObject().get("materials");
            String materialsString = materials.toString();
        
        
            CreateShipmentMapper createShipmentMapper = new CreateShipmentMapper();
            CreateShipmentP44 createShipmentP44 = new CreateShipmentP44();
        
            createShipmentP44 = createShipmentMapper.mapCreateShipment(createShipmentP44, containerID, bmwShipmentId, bookingNumber,
                    billOfLading, bmwBusinessRelation, senderId, senderName, recipientID, recipientName, recipientUnloadingPoint,
                    carrierID, carrierP44ID, carrierName, planPickUpDate, planDeliveryDate, totalWeight, totalVolume);
            Gson gson = new Gson();
            String requestBody = gson.toJson(createShipmentP44);
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://na12.api.project44.com/api/v4/shipments/tracking", entity, String.class);
            String jsonResponse = response.getBody();
        
            JsonObject shipmentIdJSON = (JsonObject) JsonParser.parseString(jsonResponse);
            String masterShipmentId = shipmentIdJSON.get("id").getAsString();
        
            String p44BookingNumber = "";
            if (!billOfLading.equals("")) {
                p44BookingNumber = billOfLading;
            } else if (!bookingNumber.equals("")) {
                p44BookingNumber = bookingNumber;
            }
            ShipmentIdMapper shipmentIdMapper = new ShipmentIdMapper();
            shipmentIdMapper.getShipmentId(masterShipmentId, p44BookingNumber, carrierP44ID, materialsString);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}