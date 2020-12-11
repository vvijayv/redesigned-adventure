package com.fk.poc.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "amazon")
@Data
public class AmazonProperties {

    @NestedConfigurationProperty
    private Aws aws;

    @NestedConfigurationProperty
    private S3 s3;

    @Data
    @ToString
    public static class Aws {

        private String accessKeyId;
        private String accessKeySecret;

    }

    @Data
    @ToString
    public static class S3 {
        private String bucket;
        private String endpoint;
        private String region;
        private String key;

    }

}
