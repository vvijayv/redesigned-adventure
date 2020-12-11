package com.fk.poc.processor;

import com.fk.poc.api.TestRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HelloMessageProcessor {
    static final Logger LOG = LoggerFactory.getLogger(HelloMessageProcessor.class);

    @Autowired
    TestRepository testRepository;

    @KafkaListener(topics = "my-topic-1")
    public void processHello(String message) {
        LOG.info("received and processed message, {}, {}", message, Thread.currentThread().getName());
        testRepository.save(message.toUpperCase());
    }
}
