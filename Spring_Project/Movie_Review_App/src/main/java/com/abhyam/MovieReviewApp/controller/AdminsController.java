package com.abhyam.MovieReviewApp.controller;

import com.abhyam.MovieReviewApp.model.Admins;
import com.abhyam.MovieReviewApp.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminsController {

    @Autowired
    private AdminsService service;

    @PostMapping("/register")
    public Admins registerAdmin(@RequestBody Admins admin){
        return service.registerAdmin(admin);
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestBody Admins admin){
        return service.verifyAdmin(admin);
    }
}
