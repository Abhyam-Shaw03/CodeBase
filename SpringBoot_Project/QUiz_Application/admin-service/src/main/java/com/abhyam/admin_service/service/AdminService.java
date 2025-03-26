package com.abhyam.admin_service.service;

import com.abhyam.admin_service.model.Admin;
import com.abhyam.admin_service.repository.AdminRepo;
import com.abhyam.admin_service.security.JWTService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AdminService {

    @Autowired
    private AdminRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Admin registerAdmin(Admin admin){
        admin.setPassword(encoder.encode(admin.getPassword()));
        return repo.save(admin);
    }

    public String verifyAdmin(Admin admin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getAdminName(), admin.getPassword())); // here we are verifying only the username and password entered.
        if(authentication.isAuthenticated())
            return jwtService.generateToken(admin.getAdminName());

        return "Failure";
    }
}
