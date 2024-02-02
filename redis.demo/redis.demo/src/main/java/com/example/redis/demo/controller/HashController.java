package com.example.redis.demo.controller;

import com.example.redis.demo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {

    private static String PERSON_HASH_KEY = "person_list";

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/hmset")
    public void addPerson(@RequestBody Person person){
        
    }
}
