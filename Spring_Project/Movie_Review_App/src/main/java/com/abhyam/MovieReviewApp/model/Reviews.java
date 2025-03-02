package com.abhyam.MovieReviewApp.model;

import jakarta.persistence.*;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "movieId", nullable = false)
    private Movies movie;

    @Column(nullable = false)
    private int rating; // 1-5 scale

    @Column(length = 1000)
    private String review;

    public Reviews() {
    }

    public Reviews(Long reviewId, Users user, Movies movie, int rating, String review) {
        this.reviewId = reviewId;
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.review = review;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", user=" + user +
                ", movie=" + movie +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}

