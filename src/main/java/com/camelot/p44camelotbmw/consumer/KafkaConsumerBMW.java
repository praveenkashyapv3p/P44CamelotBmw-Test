package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.bmwJsonMapper.ShipmentIdMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerBMW {
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    /*Development Consumer*/
    //@KafkaListener(topics = "BMWPushLocal", groupId = "BMWPushLocalGroup")
    /*Production Consumer*/
    @KafkaListener(topics = "bmwPush", groupId = "BMWPushGroup")
    public void getBMWMessage(String message) {
        RestTemplate restTemplate = new RestTemplate();
        ShipmentIdMapper shipmentIdMapper = new ShipmentIdMapper();
        try {
            String username = "Camelot_Test@bmwsandbox.com";
            String password = "c3hx>7U9^Y";
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(username, password);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(message, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("https://na12.api.project44.com/api/v4/shipments/tracking", entity, String.class);
            String jsonResponse = response.getBody();
            System.out.println(jsonResponse);
            shipmentIdMapper.getShipmentId(jsonResponse);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}