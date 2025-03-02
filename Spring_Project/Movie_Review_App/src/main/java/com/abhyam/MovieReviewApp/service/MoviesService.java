package com.abhyam.MovieReviewApp.service;

import com.abhyam.MovieReviewApp.model.Movies;
import com.abhyam.MovieReviewApp.repository.MoviesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepo repo;

    public List<Movies> getAllMovies() {
        return repo.findAll();
    }

    public Movies getMovieById(long movieId) {
        Movies movie = repo.findById(movieId).orElse(null);
        if (movie != null) {
            System.out.println("Service Layer: " + movie); // ✅ Log the product data
        }
        return movie;
    }

    public Movies addMovie(Movies movie) {
        return repo.save(movie);
    }


    public Movies updateMovie(long movieId, Movies updatedMovie) {
        Movies movie = repo.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found!"));
        movie.setMovieName(updatedMovie.getMovieName());
        movie.setReleaseDate(updatedMovie.getReleaseDate());
        movie.setGenre(updatedMovie.getGenre());
        movie.setDescription(updatedMovie.getDescription());
        return repo.save(movie);
    }

    public void deleteMovie(long movieId) {
        repo.deleteById(movieId);
    }
}
