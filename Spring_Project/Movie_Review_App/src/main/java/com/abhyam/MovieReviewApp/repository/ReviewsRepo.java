package com.abhyam.MovieReviewApp.repository;

import com.abhyam.MovieReviewApp.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepo extends JpaRepository<Reviews, Long> {
}
