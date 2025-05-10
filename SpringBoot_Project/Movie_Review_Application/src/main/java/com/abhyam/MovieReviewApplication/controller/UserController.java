package com.abhyam.MovieReviewApplication.controller;

import com.abhyam.MovieReviewApplication.model.User;
import com.abhyam.MovieReviewApplication.service.UserService;
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
    public User registerUser(@RequestBody User user){
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        return service.verifyUser(user);
    }
}
