package com.abhyam.admin_service.security;

import com.abhyam.admin_service.model.Admin;
import com.abhyam.admin_service.model.AdminPrincipal;
import com.abhyam.admin_service.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepo adminRepo;

    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByAdminName(adminName);
        if(admin == null){
            System.out.println("Admin Not found..!!");
            throw new UsernameNotFoundException("Admin Not Found..!");
        }

        return new AdminPrincipal(admin);
    }
}
