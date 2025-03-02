package com.abhyam.MovieReviewApp.service;

import com.abhyam.MovieReviewApp.model.Users;
import com.abhyam.MovieReviewApp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Users registerUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verifyUser(Users user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())); // here we are verifying only the username and password entered.
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUserName());

        return "Failure";
    }
}
