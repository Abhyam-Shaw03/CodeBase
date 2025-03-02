package com.abhyam.MovieReviewApp.controller;

import com.abhyam.MovieReviewApp.model.Users;
import com.abhyam.MovieReviewApp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return service.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user){
        return service.verifyUser(user);
    }
}
