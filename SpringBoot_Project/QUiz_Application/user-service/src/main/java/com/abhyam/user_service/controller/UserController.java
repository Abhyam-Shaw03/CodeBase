package com.abhyam.user_service.controller;

import com.abhyam.user_service.model.Users;
import com.abhyam.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user){
        return service.verifyUser(user);
    }
}
