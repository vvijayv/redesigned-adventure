package com.fk.poc.processor;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    @NestedConfigurationProperty
    KafkaServerProperties inboundKafka;
    @NestedConfigurationProperty

    KafkaServerProperties outboundKafka;

    @Data
    public static class KafkaServerProperties {
        private List<String> bootastrapServers;
        private String groupId;
    }
}
