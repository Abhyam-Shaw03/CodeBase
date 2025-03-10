package com.abhyam.MovieReviewApplication.service;

import com.abhyam.MovieReviewApplication.model.User;
import com.abhyam.MovieReviewApplication.repository.UserRepo;
import com.abhyam.MovieReviewApplication.security.JWTService;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public User registerUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())); // here we are verifying only the username and password entered.
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "Failure";
    }
}
