/*
* {
    "movieName": "Inception",
    "releaseDate": "2010-07-16",
    "genre": "Sci-Fi",
    "description": "A skilled thief who enters people's dreams to steal secrets.", // OPTIONAL
    "rating": 9.0 // OPTIONAL, IF U DO NOT GIVE IT, IT WILL BE BY DEFAULT ENTERED AS 0.0
}
* */

package com.abhyam.MovieReviewApp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(nullable = false, unique = true)
    private String movieName;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String genre;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Double rating = 0.0; // This will be updated dynamically

    public Movies() {
    }

    public Movies(Long movieId, String movieName, LocalDate releaseDate, String genre, String description, Double rating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}

