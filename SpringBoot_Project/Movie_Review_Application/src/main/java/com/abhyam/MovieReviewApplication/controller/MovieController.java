package com.abhyam.MovieReviewApplication.controller;

import com.abhyam.MovieReviewApplication.dto.MovieRequestDto;
import com.abhyam.MovieReviewApplication.dto.MovieResponseDto;
import com.abhyam.MovieReviewApplication.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")

public class MovieController {

    @Autowired
    private MovieService movieService;

    // Add a new movie (Admin only)
    @PostMapping("/add")
    public ResponseEntity<MovieResponseDto> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        return ResponseEntity.ok(movieService.addMovie(movieRequestDto));
    }

    // Get all movies
    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // Get a movie by ID
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    // ✅ Update a Movie (Admin Only)
    @PutMapping("/{movieId}")
    public ResponseEntity<MovieResponseDto> updateMovie(
            @PathVariable Long movieId,
            @RequestBody MovieRequestDto movieRequestDto) {
        return ResponseEntity.ok(movieService.updateMovie(movieId, movieRequestDto));
    }

    // ✅ Delete a Movie (Admin Only)
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
