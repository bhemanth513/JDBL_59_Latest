package com.example.springsecurity.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringController {

    @GetMapping("/home")                //anyone
    public String sayHello(){
        return "Welcome!!";
    }

    @GetMapping("/employee")            //employee
    public String emp(){
        return "Hi Emp!!";
    }


    @GetMapping("/manager")             //manager
    public String manager(){
        return "Hi Manager!!";
    }

    @GetMapping("/library")
    public String library(){
        return "Welcome to the library!!";
    }
}
