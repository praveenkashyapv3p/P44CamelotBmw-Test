package com.camelot.p44camelotbmw.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String P44TOPIC = "p44Data";
    private static final String BMWTOPIC = "bmwData";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void writeP44Message(String id, String msg) {
        //System.out.println(msg);
        this.kafkaTemplate.send(P44TOPIC, id, msg);
    }

    public void writeBMWMessage(String id, String msg) {
        //System.out.println(msg);
        this.kafkaTemplate.send(BMWTOPIC, id, msg);
    }

}
