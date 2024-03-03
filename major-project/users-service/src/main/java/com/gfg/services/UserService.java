package com.gfg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.dto.UserCreateRequest;
import com.gfg.model.User;
import com.gfg.repositories.UserCacheRepository;
import com.gfg.repositories.UserRepository;
import com.gfg.utils.Constants;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    UserCacheRepository userCacheRepository;

    @Autowired
    ObjectMapper objectMapper;
    public void createUser(UserCreateRequest userCreateRequest) throws JsonProcessingException {
        User user = userCreateRequest.to();
        userRepository.save(user);

        //TODO: communicate to wallet service to create a user wallet
        //Ex:Wallet createtion takes 3-5 sec.. u don't want wait your user till that time
        //so it is Asynchronuous via kafka

        //creating kafka topic
        JSONObject event = objectMapper.convertValue(user, JSONObject.class);
        String msg = objectMapper.writeValueAsString(event);
        kafkaTemplate.send(Constants.USER_CREATED_TOPIC,msg);

    }

    public User getUser(int userId) {
        User user = userCacheRepository.getUser(userId);
        if(user == null){
            user = userRepository.findById(userId).orElseThrow(() ->new NoSuchElementException());
            userCacheRepository.create(user);
        }
        return user;
    }
}
