package com.abhyam.user_service.security;

import com.abhyam.user_service.model.UserPrincipal;
import com.abhyam.user_service.model.Users;
import com.abhyam.user_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepo.findByUserName(userName);
        if(user == null){
            System.out.println("User Not found..!!");
            throw new UsernameNotFoundException("User Not Found..!");
        }

        return new UserPrincipal(user);
    }
}
