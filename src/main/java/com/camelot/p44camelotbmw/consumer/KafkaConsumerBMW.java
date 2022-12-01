package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.bmwJsonMapper.CreateShipmentMapper;
import com.camelot.p44camelotbmw.bmwJsonMapper.ShipmentIdMapper;
import com.camelot.p44camelotbmw.constants.CarrierMapping;
import com.camelot.p44camelotbmw.db.model.BmwCreateShipmentModel;
import com.camelot.p44camelotbmw.db.repository.BmwCreateShipmentRepository;
import com.camelot.p44camelotbmw.entity.createShipmentEntity.CreateShipmentP44;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
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
    private final KafkaProducer producer;
    @Autowired
    BmwCreateShipmentRepository bmwCreateShipmentRepository;
    
    public KafkaConsumerBMW(KafkaProducer producer) {
        this.producer = producer;
    }
    
    /*Development Consumer*/
    @KafkaListener(topics = "BMWPushLocal", groupId = "BMWPushLocalGroup")
    /*Production Consumer*/
    //@KafkaListener(topics = "bmwPushTest", groupId = "BMWPushGroupTest")
    public void getBMWMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        CarrierMapping carrierMapping = new CarrierMapping();
        String bmwShipmentId = "", containerID = "", transportationNetwork = "", carrierID = "", carrierP44ID = "", carrierName = "",
                senderId = "", senderName = "", recipientID = "", recipientName = "", recipientUnloadingPoint = "", planPickUpDate = "",
                planDeliveryDate = "", totalWeight = "", totalWeightUnit = "", totalVolume = "", totalVolumeUnit = "", bookingNumber = "",
                billOfLading = "", bmwBusinessRelation = "", materialsString = "", masterShipmentId = "", relatedShipmentId = "";
        boolean isPUT = false;
    
        try {
        
            JsonObject inputJSON = (JsonObject) JsonParser.parseString(message);
        
            /*
             * identifier
             */
            JsonElement identifiers = inputJSON.getAsJsonObject().get("identifier");
            containerID = identifiers.getAsJsonObject().get("containerId").getAsString();
            if (identifiers.getAsJsonObject().has("billOfLading"))
                billOfLading = identifiers.getAsJsonObject().get("billOfLading").getAsString();
            bmwShipmentId = identifiers.getAsJsonObject().get("bmwShipmentId").getAsString();
            if (identifiers.getAsJsonObject().has("bookingNumber"))
                bookingNumber = identifiers.getAsJsonObject().get("bookingNumber").getAsString();
            if (identifiers.getAsJsonObject().has("bmwBusinessRelation"))
                bmwBusinessRelation = identifiers.getAsJsonObject().get("bmwBusinessRelation").getAsString();
        
        
            /*
             * transportationNetwork(Not mapped. Fixed value SHIP is returned to BMW)
             */
            transportationNetwork = inputJSON.getAsJsonObject().get("transportationNetwork").getAsString();
            
            /*
             * recipient
             * UnloadingPoint is ignored for now
             */
            if (inputJSON.getAsJsonObject().has("recipient")) {
                JsonElement recipient = inputJSON.getAsJsonObject().get("recipient");
                recipientID = recipient.getAsJsonObject().get("id").getAsString();
                recipientName = recipient.getAsJsonObject().get("name").getAsString();
                if (recipient.getAsJsonObject().has("unloadingPoint"))
                    recipientUnloadingPoint = recipient.getAsJsonObject().get("unloadingPoint").getAsString();
            }
            /*
             * Sender
             */
            if (inputJSON.getAsJsonObject().has("sender")) {
                JsonElement sender = inputJSON.getAsJsonObject().get("sender");
                senderId = sender.getAsJsonObject().get("id").getAsString();
                senderName = sender.getAsJsonObject().get("name").getAsString();
            }
            /*
             * carrier
             */
            if (inputJSON.getAsJsonObject().has("carrier")) {
                JsonElement carrier = inputJSON.getAsJsonObject().get("carrier");
                carrierID = carrier.getAsJsonObject().get("id").getAsString();
                Map<String, String> stCod = carrierMapping.getCarrierId();
                carrierP44ID = stCod.get(carrierID);
                carrierName = carrier.getAsJsonObject().get("name").getAsString();
            }
            /*
             * deliveryInformation
             */
            if (inputJSON.getAsJsonObject().has("deliveryInformation")) {
                JsonElement deliveryInformation = inputJSON.getAsJsonObject().get("deliveryInformation");
                planPickUpDate = deliveryInformation.getAsJsonObject().get("planPickUpDate").getAsString();
                planDeliveryDate = deliveryInformation.getAsJsonObject().get("planDeliveryDate").getAsString();
            }
            
            /*
             * containerDimension
             * totalVolumeUnit and totalWeightUnit are ignored for now
             */
            if (inputJSON.getAsJsonObject().has("containerDimension")) {
                JsonElement containerDimensions = inputJSON.getAsJsonObject().get("containerDimension");
                totalVolume = containerDimensions.getAsJsonObject().get("totalVolume").getAsString();
                if (containerDimensions.getAsJsonObject().has("totalVolumeUnit"))
                    totalVolumeUnit = containerDimensions.getAsJsonObject().get("totalVolumeUnit").getAsString();
                totalWeight = containerDimensions.getAsJsonObject().get("totalWeight").getAsString();
                if (containerDimensions.getAsJsonObject().has("totalWeightUnit"))
                    totalWeightUnit = containerDimensions.getAsJsonObject().get("totalWeightUnit").getAsString();
            }
            /*
             * materials
             */
            if (inputJSON.getAsJsonObject().has("materials")) {
                JsonArray materials = (JsonArray) inputJSON.getAsJsonObject().get("materials");
                materialsString = materials.toString();
            }
        
            String p44BookingNumber = "";
            if (!billOfLading.equals("")) {
                p44BookingNumber = billOfLading;
                List<BmwCreateShipmentModel> byBillOfLading = bmwCreateShipmentRepository.findByBillOfLading(billOfLading);
                for (BmwCreateShipmentModel bmwCreateShipmentModel : byBillOfLading) {
                    masterShipmentId = bmwCreateShipmentModel.getShipmentId();
                    relatedShipmentId = bmwCreateShipmentModel.getRelatedShipmentId();
                    isPUT = true;
                }
            } else if (!bookingNumber.equals("")) {
                p44BookingNumber = bookingNumber;
                List<BmwCreateShipmentModel> byBookingNumber = bmwCreateShipmentRepository.findByBookingNumber(bookingNumber);
                for (BmwCreateShipmentModel bmwCreateShipmentModel : byBookingNumber) {
                    masterShipmentId = bmwCreateShipmentModel.getShipmentId();
                    relatedShipmentId = bmwCreateShipmentModel.getRelatedShipmentId();
                    isPUT = true;
                }
            }
        
            CreateShipmentMapper createShipmentMapper = new CreateShipmentMapper();
            CreateShipmentP44 createShipmentP44 = new CreateShipmentP44();
        
            createShipmentP44 = createShipmentMapper.mapCreateShipment(createShipmentP44, containerID, bmwShipmentId, bookingNumber, billOfLading, bmwBusinessRelation, senderId, senderName, recipientID, recipientName, recipientUnloadingPoint, carrierID, carrierP44ID, carrierName, planPickUpDate, planDeliveryDate, totalWeight, totalVolume, masterShipmentId, relatedShipmentId);
            Gson gson = new Gson();
            String requestBody = gson.toJson(createShipmentP44);
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://na12.api.project44.com/api/v4/shipments/tracking", entity, String.class);
            String jsonResponse = response.getBody();
        
            JsonObject shipmentIdJSON = (JsonObject) JsonParser.parseString(jsonResponse);
            String shipmentId = shipmentIdJSON.get("id").getAsString();
            JsonArray relatedShipments = (JsonArray) shipmentIdJSON.get("relatedShipments");
            for (JsonElement ShipmentId : relatedShipments) {
                relatedShipmentId = ShipmentId.getAsJsonObject().get("id").getAsString();
            }
        
            BmwCreateShipmentModel bmwCreateShipmentModel = new BmwCreateShipmentModel();
            bmwCreateShipmentModel.setShipmentId(shipmentId);
            bmwCreateShipmentModel.setBookingNumber(bookingNumber);
            bmwCreateShipmentModel.setBillOfLading(billOfLading);
            bmwCreateShipmentModel.setRelatedShipmentId(relatedShipmentId);
            bmwCreateShipmentRepository.updateUsingFindAndReplace(shipmentId, bmwCreateShipmentModel);
        
            ShipmentIdMapper shipmentIdMapper = new ShipmentIdMapper();
            shipmentIdMapper.getShipmentId(relatedShipmentId, isPUT, p44BookingNumber, carrierP44ID, materialsString, message, producer);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split consumer: " + e);
            bmwErrorMsgLog(message);
        }
    }
    
    public void bmwErrorMsgLog(String message) {
        this.producer.writeLogErrorMessage("test-bmw-message-error", message);
    }
}