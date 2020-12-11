package com.fk.poc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SpringbootTestcontainersDemoApplicationTests extends AbstractIntegrationTestConfiguration {
    @Test
    public void testContext() {
        assertThat("actual").isNotEqualTo("other");
    }
}
