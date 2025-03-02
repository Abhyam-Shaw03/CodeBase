//THIS CLASS IS FOR WORKING OF USER DETAILS SERVICE FOR AUTHENTICATION PROVIDER IN SECURITYCONFIG FILE

package com.abhyam.MovieReviewApp.service;

import com.abhyam.MovieReviewApp.model.AdminPrincipal;
import com.abhyam.MovieReviewApp.model.Admins;
import com.abhyam.MovieReviewApp.repository.AdminsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminsRepo repo;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {

        Admins admin = repo.findByAdminName(adminName);
        if(admin == null){
            System.out.println("Not Found..!!");
            throw new UsernameNotFoundException("Username Not Found..!!");
        }
        return new AdminPrincipal(admin);
    }
}