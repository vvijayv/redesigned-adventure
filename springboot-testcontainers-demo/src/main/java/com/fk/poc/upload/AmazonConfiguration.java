package com.fk.poc.upload;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {
    @Autowired
    AmazonProperties amazonProperties;

    @Bean
    public AmazonS3 s3() {
        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard()
                .withRegion(amazonProperties.getS3().getRegion());
        if (amazonProperties.getS3().getEndpoint() != null) {
            clientBuilder.withEndpointConfiguration(new EndpointConfiguration(amazonProperties.getS3().getEndpoint(),
                    amazonProperties.getS3().getRegion()));
        }
        clientBuilder.withCredentials(getCredentials());
        return clientBuilder.build();
    }

    private AWSCredentialsProvider getCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonProperties.getAws().getAccessKeyId(),
                amazonProperties.getAws().getAccessKeySecret()));
    }

}
