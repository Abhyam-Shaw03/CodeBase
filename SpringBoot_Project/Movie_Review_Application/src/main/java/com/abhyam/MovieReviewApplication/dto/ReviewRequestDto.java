package com.abhyam.MovieReviewApplication.dto;

public class ReviewRequestDto {

    private Long movieId;
    private int rating;
    private String comment;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReviewRequestDto() {
    }

    public ReviewRequestDto(Long movieId, int rating, String comment) {
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ReviewRequestDto{" +
                "movieId=" + movieId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
