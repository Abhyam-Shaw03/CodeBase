/*
* {
    "adminName": "aradhya",
    "password": "aradhya",
    "phone": 7896549871,
    "email": "aradhya@gmail.com"
}
* */


package com.abhyam.MovieReviewApp.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Pattern;

@Entity
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(nullable = false, unique = true)
    private String adminName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 10)
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;

    @Column(nullable = false, unique = true)
//    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email pattern")
    private String email;

    public Admins() {
    }

    public Admins(Long adminId, String adminName, String password, String phone, String email) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

