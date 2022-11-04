package com.camelot.p44camelotbmw.controller;


import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
public class CreateShipmentController {
    private final KafkaProducer producer;
    
    public CreateShipmentController(KafkaProducer producer) {
        this.producer = producer;
    }
    
    @PostMapping(value = "/v1/createShipment", consumes = "application/json")
    public ResponseEntity<?> shipmentDetailsFromP44(@RequestBody String message) {
        String jsonKey = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
        this.producer.writeBMWPushMessage("test" + jsonKey, message);
        return ResponseEntity.ok().build();
    }
    
}