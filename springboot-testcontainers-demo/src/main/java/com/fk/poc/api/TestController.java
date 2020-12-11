package com.fk.poc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestRepository redisRepository;

    @GetMapping("/test")
    public List<String> test() {
        return redisRepository.getAll();
    }

    @PostMapping("/test/{message}")
    public String testMessage(@PathVariable String message) {
        return "test-" + redisRepository.save(message);
    }
}
