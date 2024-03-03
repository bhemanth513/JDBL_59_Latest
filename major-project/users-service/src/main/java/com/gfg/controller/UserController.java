package com.gfg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gfg.model.User;
import com.gfg.services.UserService;
import com.gfg.dto.UserCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("")
    public void createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) throws JsonProcessingException {
        userService.createUser(userCreateRequest);
    }

    @GetMapping("")
    public User getUser(@RequestParam("userId") int userId){
        return this.userService.getUser(userId);
    }
}
