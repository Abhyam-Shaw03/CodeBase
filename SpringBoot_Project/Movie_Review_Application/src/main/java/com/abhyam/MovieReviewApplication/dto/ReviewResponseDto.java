package com.abhyam.MovieReviewApplication.dto;

import com.abhyam.MovieReviewApplication.model.Review;

import java.time.LocalDateTime;

public class ReviewResponseDto {

    private String username;
    private double rating;
    private String comment;
    private LocalDateTime createdAt;

    public ReviewResponseDto(Review review) {
        this.username = review.getUser().getUsername();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.createdAt = review.getCreatedAt();
    }

    public String getUsername() {
        return username;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
