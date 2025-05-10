package com.abhyam.admin_service.controller;

import com.abhyam.admin_service.model.Admin;
import com.abhyam.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody Admin admin){
        return service.registerAdmin(admin);
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestBody Admin admin){
        return service.verifyAdmin(admin);
    }
}
