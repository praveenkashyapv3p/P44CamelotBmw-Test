package com.camelot.p44camelotbmw.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class kafkaConsumer {
    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "p44Data", groupId = "p44Group")
    public void getMessage(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://p44-tracking-data-int.bmwgroup.com", entity, String.class);
        System.out.println(response.getBody());
    }

}