package com.abhyam.MovieReviewApplication.dto;

import com.abhyam.MovieReviewApplication.model.Movie;

public class MovieResponseDto {
    private Long id;
    private String title;
    private String genre;
    private String description;
    private String releaseDate;
    private Double rating;

    public MovieResponseDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.genre = movie.getGenre();
        this.description = movie.getDescription();
        this.releaseDate = movie.getReleaseDate();
        this.rating = movie.getRating();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getRating() {
        return rating;
    }
}
