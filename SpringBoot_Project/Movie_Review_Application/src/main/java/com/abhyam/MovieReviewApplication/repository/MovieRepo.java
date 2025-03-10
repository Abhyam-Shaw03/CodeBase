package com.abhyam.MovieReviewApplication.repository;

import com.abhyam.MovieReviewApplication.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
}
