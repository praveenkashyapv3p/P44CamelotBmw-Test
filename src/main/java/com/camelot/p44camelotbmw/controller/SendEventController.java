package com.camelot.p44camelotbmw.controller;

import com.camelot.p44camelotbmw.db.model.MetricsModel;
import com.camelot.p44camelotbmw.db.repository.MetricsRepository;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SendEventController {
    private static final Logger logger = LogManager.getLogger(SendEventController.class);
    private final KafkaProducer producer;
    private final AtomicLong atomicLong = new AtomicLong();
    MetricsRepository metricsRepository;
    
    @Autowired
    public SendEventController(KafkaProducer producer, MetricsRepository metricsRepository) {
        this.producer = producer;
        this.metricsRepository = metricsRepository;
    }
    
    @GetMapping(value = "/v1/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is running!");
    }
    
    @GetMapping(value = "/")
    public ResponseEntity<String> getCheck() {
        return ResponseEntity.ok("API is running! Please use complete endpoint.");
    }
    
    @PostMapping(value = "/v1/sendEvent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> shipmentDetailsFromP44(@RequestBody String shipmentJson) {
        MetricsModel metricsModel = new MetricsModel();
        String today = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        metricsModel.setDate(today);
        List<MetricsModel> metricsCreate = metricsRepository.findByDate(today);
        if (metricsCreate.size() < 1)
            atomicLong.set(0);
        for (MetricsModel metricsModelRes : metricsCreate) {
            metricsModel.setMapped(metricsModelRes.getMapped());
            metricsModel.setSent(metricsModelRes.getSent());
        }
        metricsModel.setReceived(atomicLong.incrementAndGet());
        metricsRepository.upsert(metricsModel);
    
        try {
            boolean trackingJson = false;
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