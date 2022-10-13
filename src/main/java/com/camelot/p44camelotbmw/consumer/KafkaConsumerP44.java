package com.camelot.p44camelotbmw.consumer;

import com.camelot.p44camelotbmw.p44JsonMapper.MessageMapper;
import com.camelot.p44camelotbmw.producer.KafkaProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerP44 {
    
    private static final Logger logger = LogManager.getLogger(KafkaConsumerP44.class);
    private final KafkaProducer producer;
    @Autowired
    private RestTemplate restTemplate;
    
    public KafkaConsumerP44(KafkaProducer producer) {
        this.producer = producer;
    }
    
    
    /*Development Consumer*/
    //@KafkaListener(topics = "p44DataLocal", groupId = "p44DataLocalGroup")
    /*Production Consumer*/
    @KafkaListener(topics = "p44Data", groupId = "bmwGroup")
    public void getP44Message(String message) {
        MessageMapper messageMapper = new MessageMapper();
        String bmwMessage = messageMapper.mapMessage(message, producer);
        if (!(bmwMessage.equals(""))) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(bmwMessage, headers);
                ResponseEntity<String> response = restTemplate.postForEntity("https://p44-tracking-data-dev.bmwgroup.com", entity, String.class);
            } catch (Exception exception) {
                logger.error("Unable to push to BMW: " + exception);
                producer.writeBMWErrorMessage(exception.getMessage(), bmwMessage);
            }
        } else {
            producer.writeBMWMapError(message);
        }
    }
    
}