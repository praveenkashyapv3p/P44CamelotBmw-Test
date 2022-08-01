package com.camelot.p44camelotbmw.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    
    /*Development Topic*/
    //private static final String P44TOPIC = "p44DataLocal";
    /*Production Topic*/
    private static final String P44TOPIC = "p44Data";
    /*Development Topic*/
    //private static final String BMWTOPIC = "bmwDataLocal";
    /*Production Topic*/
    private static final String BMWTOPIC = "bmwData";
    /*Development Topic*/
    //private static final String BMWPUSH = "bmwPushLocal";
    /*Production Topic*/
    private static final String BMWPUSH = "bmwPush";
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void writeP44Message(String id, String msg) {
        this.kafkaTemplate.send(P44TOPIC, id, msg);
    }
    
    public void writeBMWMessage(String id, String msg) {
        this.kafkaTemplate.send(BMWTOPIC, id, msg);
    }
    
    public void writeBMWPushMessage(String id, String msg) {
        this.kafkaTemplate.send(BMWPUSH, id, msg);
    }
    
}