package com.camelot.p44camelotbmw.bmwJsonMapper;

import com.camelot.p44camelotbmw.consumer.KafkaConsumerBMW;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class ShipmentIdMapper {
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    public void getShipmentId(String responseJson) {
        RestTemplate restTemplate = new RestTemplate();
        JsonObject relShipIdentJSON = (JsonObject) JsonParser.parseString(responseJson);
        String url = "https://na12.api.project44.com/api/v4/shipments/" + (relShipIdentJSON.get("id")).getAsString().replace("\"", "") + "/tracking";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("Camelot_Test@bmwsandbox.com", "c3hx>7U9^Y");
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            String jsonResponse = response.getBody();
            System.out.println(jsonResponse);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}