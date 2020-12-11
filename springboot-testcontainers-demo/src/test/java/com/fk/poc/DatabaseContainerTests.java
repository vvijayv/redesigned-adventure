package com.fk.poc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class DatabaseContainerTests {
    @Container
    static PostgreSQLContainer<?> psql = new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
            .withDatabaseName("foo").withUsername("foo").withPassword("secret");

    @Test
    public void shouldTestRedisRunning() {
        assertThat(psql.isRunning()).isTrue();
    }
}
