package com.abhyam.admin_service.repository;

import com.abhyam.admin_service.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Admin findByAdminName(String adminName);
}
