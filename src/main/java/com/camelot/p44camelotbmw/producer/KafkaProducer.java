package com.camelot.p44camelotbmw.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    
    /*Development Topic*/
    //private static final String P44TOPIC = "p44DataLocal";
    /*Production Topic*/
    private static final String P44TOPIC = "p44DataTest";
    /*Development Topic*/
    //private static final String BMWTOPIC = "bmwDataLocal";
    /*Production Topic*/
    private static final String BMWTOPIC = "bmwDataTest";
    private static final String BMWPUSH = "bmwPushTest";
    private static final String LOGPUSH = "logTopic";
    private static final String BMWERRORTOPIC = "bmwError";
    
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    public void writeP44Message(String id, String msg) {
        this.kafkaTemplate.send(P44TOPIC, id, msg);
    }
    
    public void writeBMWMessage(String id, String msg) {
        this.kafkaTemplate.send(BMWTOPIC, id, msg);
    }
    
    public void writeBMWErrorMessage(String id, String msg) {
        this.kafkaTemplate.send(BMWERRORTOPIC, id, msg);
    }
    
    public void writeBMWPushMessage(String id, String msg) {
        this.kafkaTemplate.send(BMWPUSH, id, msg);
    }
    
    public void writeLogMessage(String id, String msg) {
        this.kafkaTemplate.send(LOGPUSH, id, msg);
    }
    
}