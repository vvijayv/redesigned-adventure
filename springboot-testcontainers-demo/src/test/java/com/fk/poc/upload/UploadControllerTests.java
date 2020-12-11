package com.fk.poc.upload;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fk.poc.AbstractIntegrationTestConfiguration;
import com.fk.poc.api.TestRepository;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;

public class UploadControllerTests extends AbstractIntegrationTestConfiguration {

    @Autowired
    TestRepository testRepository;

    @Autowired
    AmazonProperties amazonProperties;

    @Test
    public void shouldUploadFile() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "hello_world.txt", "text",
                "hello world".getBytes());
        mockMvc.perform(MockMvcRequestBuilders.multipart("/files").file(mockMultipartFile)).andExpect(status().isOk())
                .andExpect(content().string("hello_world.txt"));
    }

    @Test
    public void listAllUploadedFiles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/files"))
                .andExpect(MockMvcResultMatchers.content().string(StringContains.containsString("hello_world.txt")));
    }

    @TestConfiguration
    public static class S3Configuration {

        @Autowired
        AmazonProperties amazonProperties;

        @Primary
        @Bean(destroyMethod = "shutdown")
        public AmazonS3 amazonS3() {
            AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(localstack.getEndpointConfiguration(Service.S3))
                    .withCredentials(localstack.getDefaultCredentialsProvider()).build();
            amazonS3.createBucket(amazonProperties.getS3().getBucket());
            return amazonS3;
        }

    }

}
