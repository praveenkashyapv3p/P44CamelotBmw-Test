package com.camelot.p44camelotbmw.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerBMW {
    private static final Logger logger = LogManager.getLogger(KafkaConsumerBMW.class);
    
    /*Development Consumer*/
    @KafkaListener(topics = "BMWPushLocal", groupId = "BMWPushLocalGroup")
    /*Production Consumer*/
    // @KafkaListener(topics = "BMWPush", groupId = "BMWPushGroup")
    public void getBMWMessage(String message) {
        try {
            System.out.println(message);
        } catch (Exception e) {
            logger.error("BMW Message cannot be split: " + e);
        }
    }
}