package com.camelot.p44camelotbmw.controller;

import com.camelot.p44camelotbmw.jsonmapper.TechnicalDetailsMapper;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
            boolean trackingJson = false;
            String jsonKey = String.valueOf(TechnicalDetailsMapper.get64MostSignificantBitsForVersion1());
            JsonObject ShipJSON = (JsonObject) JsonParser.parseString(shipmentJson);
            JsonArray events = (JsonArray) ShipJSON.get("events");
            for (JsonElement eventsTyp : events) {
                if (eventsTyp.getAsJsonObject().get("type").getAsString().equals("TRACKING_START") && eventsTyp.getAsJsonObject().get("type").getAsString().equals("UNKNOWN")) {
                    trackingJson = true;
                }
            }
            if (!trackingJson) {
                this.producer.writeP44Message(jsonKey, shipmentJson);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}