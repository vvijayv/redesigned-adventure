package com.fk.poc;

import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

// @ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTestConfiguration {
    @Autowired
    public MockMvc mockMvc;

    @ClassRule
    public static Network network = Network.newNetwork();
    @ClassRule
    public static final LocalStackContainer localstack = new LocalStackContainer(
            DockerImageName.parse("localstack/localstack:0.11.3")).withNetwork(network).withServices(Service.S3)
                    .waitingFor(Wait.forListeningPort());

    @ClassRule
    public static final GenericContainer<?> redisContainer = new GenericContainer<>(
            DockerImageName.parse("redis:6.0.9-alpine")).withExposedPorts(6379).withNetwork(network)
                    .waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\n", 1));

    @BeforeAll
    public static void init() {
        redisContainer.start();
        localstack.start();
        System.setProperty("REDIS_HOST", String.valueOf(redisContainer.getHost()));
        System.setProperty("REDIS_PORT", String.valueOf(redisContainer.getFirstMappedPort()));
        // localstack.getAccessKey()
    }
}
