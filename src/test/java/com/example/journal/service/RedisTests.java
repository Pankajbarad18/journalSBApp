package com.example.journal.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void testRedis(){
       redisTemplate.opsForValue().set("email","email@gmail.com");
       String email = redisTemplate.opsForValue().get("email").toString();
       System.out.println(email);

        System.out.println("1");
    }
}
