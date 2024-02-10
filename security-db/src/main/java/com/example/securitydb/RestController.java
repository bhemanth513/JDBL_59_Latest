package com.example.securitydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    DemoUserService demoUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/home")                //anyone
    public String sayHello(){
        return "Welcome!!";
    }

    @GetMapping("/faculty")            //employee
    public String emp(){
        return "Hi Emp!!";
    }


    @GetMapping("/student")             //manager
    public String manager(){
        return "Hi Manager!!";
    }

    @GetMapping("/library")
    public String library(){
        return "Welcome to the library!!";
    }

    @PostMapping("/faculty/signup")
    public DemoUser facultySignup(@RequestParam("username") String username,
                                  @RequestParam("password") String password){
        DemoUser demoUser = DemoUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authorities("login_faculty::access_library")
                .build();
        //convert plain text to encrypted
        //save it to repository
        return demoUserService.save(demoUser);
    }

    @PostMapping("/student/signup")
    public DemoUser studentSignup(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        //1.assign authorities
        DemoUser demoUser = DemoUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .authorities("login_student::access_library")
                .build();
        //convert plain text to encrypted
        //save it to repository
        return demoUserService.save(demoUser);
    }
}
