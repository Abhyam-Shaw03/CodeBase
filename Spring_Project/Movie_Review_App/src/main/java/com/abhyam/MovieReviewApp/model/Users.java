/*
* {
    "userName": "aradhya",
    "password": "aradhya",
    "phone": 7896549871,
    "email": "aradhya@gmail.com"
}
* */

package com.abhyam.MovieReviewApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, unique = true, length = 10)
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
//    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email pattern")
    private String email;

    public Users() {
    }

    public Users(Long userId, String userName, String phone, String password, String email) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

