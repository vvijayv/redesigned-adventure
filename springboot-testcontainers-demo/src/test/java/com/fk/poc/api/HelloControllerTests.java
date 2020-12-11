package com.fk.poc.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fk.poc.AbstractIntegrationTestConfiguration;

import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class HelloControllerTests extends AbstractIntegrationTestConfiguration {

    @Autowired
    TestRepository testRepository;

    @Test
    public void shouldPostMessage() throws Exception {
        mockMvc.perform(post("/test/test-message").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(StringContains.containsString("test-test-message")));
    }

    @Test
    public void shouldGetAllPostedMessages() throws Exception {
        testRepository.save("test-message");
        mockMvc.perform(get("/test")).andExpect(content().string(StringContains.containsString("test-message")));
    }

}
