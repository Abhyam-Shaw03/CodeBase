package com.abhyam.MovieReviewApp.service;

import com.abhyam.MovieReviewApp.model.Admins;
import com.abhyam.MovieReviewApp.repository.AdminsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminsService {

    @Autowired
    private AdminsRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Admins registerAdmin(Admins admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        return repo.save(admin);
    }

    public String verifyAdmin(Admins admin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getAdminName(), admin.getPassword())); // here we are verifying only the username and password entered.
        if(authentication.isAuthenticated())
            return jwtService.generateToken(admin.getAdminName());

        return "Failure";
    }
}
