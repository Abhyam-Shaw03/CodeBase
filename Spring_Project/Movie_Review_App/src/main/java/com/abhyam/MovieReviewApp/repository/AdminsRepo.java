package com.abhyam.MovieReviewApp.repository;

import com.abhyam.MovieReviewApp.model.Admins;
import com.abhyam.MovieReviewApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepo extends JpaRepository<Admins, Long> {
    Admins findByAdminName(String adminName);
}
