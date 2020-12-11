package com.fk.poc.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public String save(String message) {
        return message + "-" + redisTemplate.opsForList().leftPush("tests", message);
    }

    public List<String> getAll() {
        return redisTemplate.opsForList().range("tests", 0, -1);
    }
}
