package com.fk.poc.processor;

import java.time.Duration;

import com.fk.poc.api.TestRepository;

import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9191", "port=9191" })
public class HelloMessageProcessorTests {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    TestRepository testRepo;

    @Test
    public void testA() {
        kafkaTemplate.send("my-topic-1", "hello-there");
        kafkaTemplate.send("my-topic-1", "hello-here");
        Awaitility.await().during(Duration.ofSeconds(5)).atMost(Duration.ofSeconds(15)).until(() -> testRepo.getAll(),
                Matchers.hasItems("HELLO-THERE", "HELLO-HERE"));
    }

}
