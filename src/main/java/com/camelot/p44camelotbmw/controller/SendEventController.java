package com.camelot.p44camelotbmw.controller;

import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@RestController
public class SendEventController {
    private static final Logger logger = LogManager.getLogger(SendEventController.class);
    private final KafkaProducer producer;
    
    
    public SendEventController(KafkaProducer producer) {
        this.producer = producer;
    }
    
    @GetMapping(value = "/v1/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok().build();
    }
    
    @GetMapping(value = "/")
    public ResponseEntity<String> getCheck() {
        return ResponseEntity.ok().build();
    }
    
    @PostMapping(value = "/v1/sendEvent", consumes = "application/json")
    public ResponseEntity<String> shipmentDetailsFromP44(@RequestBody String shipmentJson) {
        try {
            boolean trackingJson = false;
            //String jsonKey = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
            JsonObject ShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
            JsonArray events = (JsonArray) ShipJSON.get("events");
            for (JsonElement eventsTyp : events) {
                if (eventsTyp.getAsJsonObject().get("type").getAsString().equalsIgnoreCase("TRACKING_START") || eventsTyp.getAsJsonObject().get("type").getAsString().equalsIgnoreCase("TRACKING_INITIATE") || eventsTyp.getAsJsonObject().get("type").getAsString().equals("UNKNOWN")) {
                    trackingJson = true;
                }
                if (eventsTyp.getAsJsonObject().has("description")) {
                    String description = eventsTyp.getAsJsonObject().get("description").getAsString();
                    if (description.contains("Shipment tracking all containers completed")) trackingJson = true;
                }
            }
            if (!trackingJson) {
    
                this.producer.writeP44Message("test-" + OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX")), shipmentJson);
            }
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            logger.error("Invalid JSON or JSON Structure mismatch from Project44 message " + e + shipmentJson);
            return ResponseEntity.badRequest().build();
        }
    }
}