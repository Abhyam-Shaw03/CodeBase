package com.abhyam.MovieReviewApp.repository;

import com.abhyam.MovieReviewApp.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepo extends JpaRepository<Movies, Long> {
}
