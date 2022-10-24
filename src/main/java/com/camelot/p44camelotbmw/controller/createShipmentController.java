package com.camelot.p44camelotbmw.controller;


import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Service
@RestController
public class createShipmentController {
    private static final Logger logger = LogManager.getLogger(createShipmentController.class);
    private final KafkaProducer producer;
    
    public createShipmentController(KafkaProducer producer) {
        this.producer = producer;
    }
    
    @PostMapping(value = "/v1/createShipment", consumes = "application/json")
    public ResponseEntity<?> shipmentDetailsFromP44(@RequestBody String message, HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forward-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        logger.info("ipAddress: " + ipAddress);
        String jsonKey = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
        this.producer.writeBMWPushMessage("test" + jsonKey, message);
        return ResponseEntity.ok().build();
    }
    
}