package com.camelot.p44camelotbmw.bmwJsonMapper;

import com.camelot.p44camelotbmw.consumer.KafkaConsumerBMW;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class ShipmentIdMapper {
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    public void getShipmentId(String shipmentId, String bmwShipmentId, String carrierP44ID, String materials) {
        RestTemplate restTemplate = new RestTemplate();
        FinalP44AttributesMapper finalP44AttributesMapper = new FinalP44AttributesMapper();
        String originDest = "", origin = "", destination = "";
        String url = "https://na12.api.project44.com/api/v4/shipments/" + shipmentId + "/tracking";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            String jsonResponse = response.getBody();
            JsonObject shipmentIdJSON = (JsonObject) JsonParser.parseString(jsonResponse);
            JsonElement routeInfo = shipmentIdJSON.getAsJsonObject().get("routeInfo");
            JsonArray stops = (JsonArray) routeInfo.getAsJsonObject().get("stops");
            for (JsonElement stp : stops) {
                originDest = stp.getAsJsonObject().get("type").getAsString();
                if (originDest.equals("ORIGIN")) {
                    origin = stp.getAsJsonObject().get("id").getAsString();
                }
                if (originDest.equals("DESTINATION")) {
                    destination = stp.getAsJsonObject().get("id").getAsString();
                }
            }
            
            finalP44AttributesMapper.getAllAttributes(shipmentId, origin, destination, bmwShipmentId, carrierP44ID, materials);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}