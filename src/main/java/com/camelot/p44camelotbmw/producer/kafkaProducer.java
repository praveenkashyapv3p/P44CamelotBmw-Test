package com.camelot.p44camelotbmw.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {

    private static final String TOPIC = "p44Data";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void writeMessage(String id, String msg) {
        //System.out.println(msg);
        this.kafkaTemplate.send(TOPIC, id, msg);
    }

}
