//THIS CLASS IS FOR WORKING OF USER DETAILS SERVICE FOR AUTHENTICATION PROVIDER IN SECURITYCONFIG FILE

package com.abhyam.MovieReviewApp.service;

import com.abhyam.MovieReviewApp.model.UserPrincipal;
import com.abhyam.MovieReviewApp.model.Users;
import com.abhyam.MovieReviewApp.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = userRepo.findByUserName(userName);
        if(user == null){
            System.out.println("Not Found..!!");
            throw new UsernameNotFoundException("Username Not Found..!!");
        }
        return new UserPrincipal(user);
    }
}
