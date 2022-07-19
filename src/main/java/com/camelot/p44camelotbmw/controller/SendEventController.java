package com.camelot.p44camelotbmw.controller;

import com.camelot.p44camelotbmw.jsonmapper.TechnicalDetailsMapper;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEventController {
    private final KafkaProducer producer;
    
    
    public SendEventController(KafkaProducer producer) {
        this.producer = producer;
    }
    
    @PostMapping(value = "/v1/sendEvent", consumes = "application/json")
    public ResponseEntity<String> shipmentDetailsFromP44(@RequestBody String shipmentJson) {
        try {
            String jsonKey = String.valueOf(TechnicalDetailsMapper.get64MostSignificantBitsForVersion1());
            this.producer.writeP44Message(jsonKey, shipmentJson);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}