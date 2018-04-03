package com.bancolombia.creditcard.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
	MessageStorage storage;
    
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void listen(@Payload String message) {
        LOG.info("received message='{}'", message);
        storage.put(message);
    }

}