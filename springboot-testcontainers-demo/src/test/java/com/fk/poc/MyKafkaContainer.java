package com.fk.poc;

import java.io.IOException;

import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class MyKafkaContainer extends KafkaContainer {

    public MyKafkaContainer(DockerImageName name) {
        super(name);
    }

    public static MyKafkaContainer newInstance() {
        return new MyKafkaContainer(DockerImageName.parse("confluentinc/cp-kafka"));
    }

    @Override
    public void start() {
        super.start();
        System.getProperty("KAFKA_BOOTSTRAP_SERVERS", getBootstrapServers());
        try {
            execInContainer("/bin/sh", "-c",
                    "/usr/bin/kafka-topics --create --topic my-topic-1 --bootstrap-server localhost:9092 --partitions 2 --replication-factor 1");
        } catch (UnsupportedOperationException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
