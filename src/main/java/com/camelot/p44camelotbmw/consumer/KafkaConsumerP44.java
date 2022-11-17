package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.constants.UuidGenerator;
import com.camelot.p44camelotbmw.db.model.BmwResponseModel;
import com.camelot.p44camelotbmw.db.model.P44IncomingModel;
import com.camelot.p44camelotbmw.db.repository.BmwResponseRepository;
import com.camelot.p44camelotbmw.db.repository.P44IncomingRepository;
import com.camelot.p44camelotbmw.p44JsonMapper.MessageMapper;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class KafkaConsumerP44 {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerP44.class);
    private final KafkaProducer producer;
    @Autowired
    public P44IncomingRepository p44IncomingRepository;
    @Autowired
    BmwResponseRepository bmwResponseRepository;
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RetryTemplate retryTemplate;
    
    
    public KafkaConsumerP44(KafkaProducer producer) {
        this.producer = producer;
    }
    
    
    /*Development Consumer*/
    //@KafkaListener(topics = "p44DataLocal", groupId = "p44DataLocalGroup")
    /*Production Consumer*/
    @KafkaListener(topics = "p44DataTest", groupId = "bmwGroupTest")
    public void getP44Message(ConsumerRecord<String, String> record) {
        MessageMapper messageMapper = new MessageMapper();
        String correlationId = String.valueOf(UuidGenerator.get64MostSignificantBitsForVersion1());
        Map<String, String> bmwMessageStatus = messageMapper.mapMessage(record.key().substring(5), record.value(), producer, correlationId);
        if (bmwMessageStatus.containsKey("success")) {
            String bmwMessage = bmwMessageStatus.get("success");
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(bmwMessage, headers);
                ResponseEntity<String> response = retryTemplate.execute(context -> {
                    ResponseEntity<String> requestData = restTemplate.postForEntity("https://p44-tracking-data-int.bmwgroup.com", entity, String.class);
                    return requestData;
                });
                BmwResponseModel bmwResponseModel = new BmwResponseModel();
                bmwResponseModel.setResponseCode(response.getStatusCode().toString());
                bmwResponseModel.setResponseMessage(response.getBody());
                bmwResponseModel.setCorrelationId(correlationId);
                bmwResponseModel.setTimestamp(OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX")));
                bmwResponseRepository.save(bmwResponseModel);
            } catch (Exception exception) {
                logger.error("Unable to push to BMW: " + exception);
                producer.writeBMWErrorMessage("test-" + exception.getMessage(), bmwMessage);
            }
        } else {
            producer.writeLogMessage("test-" + "Mapping Failed", record.value());
            P44IncomingModel p44IncomingModel = new P44IncomingModel();
            JsonObject shipment = (JsonObject) JsonParser.parseString(record.value());
            p44IncomingModel.setInternalP44Identifier(shipment.get("shipment").getAsJsonObject().get("id").getAsString());
            p44IncomingModel.setTimestamp(OffsetDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSX")));
            p44IncomingModel.setCause("Mapping Failed");
            p44IncomingRepository.save(p44IncomingModel);
        }
    }
}