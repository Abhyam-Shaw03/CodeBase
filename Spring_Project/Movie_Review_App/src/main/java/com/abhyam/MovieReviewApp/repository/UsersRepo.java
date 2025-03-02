package com.abhyam.MovieReviewApp.repository;

import com.abhyam.MovieReviewApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
}
