package com.abhyam.MovieReviewApplication.controller;

import com.abhyam.MovieReviewApplication.dto.ReviewRequestDto;
import com.abhyam.MovieReviewApplication.dto.ReviewResponseDto;
import com.abhyam.MovieReviewApplication.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Add a new review (Authenticated users only)
    @PostMapping("/add")
    public ResponseEntity<ReviewResponseDto> addReview(@RequestBody ReviewRequestDto reviewRequestDto, Principal principal) {

        System.out.println("Received Review: " + reviewRequestDto);

        String username = principal.getName();  // Get logged-in user
        return ResponseEntity.ok(reviewService.addReview(username, reviewRequestDto));
    }

    // Get all reviews for a specific movie
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ReviewResponseDto>> getMovieReviews(@PathVariable Long movieId) {
        return ResponseEntity.ok(reviewService.getReviewsByMovie(movieId));
    }

    // ✅ Update a Review (Only Owner)
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDto reviewRequestDto,
            Principal principal) {
        String username = principal.getName(); // Get logged-in user
        return ResponseEntity.ok(reviewService.updateReview(username, reviewId, reviewRequestDto));
    }

    // ✅ Delete a Review (Owner or Admin, but Admin cannot delete another Admin's review)
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, Principal principal) {
        String username = principal.getName(); // Get logged-in user
        reviewService.deleteReview(username, reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }

//    // ✅ Update a Review (Only Owner)
//    @PutMapping("/{reviewId}")
//    public ResponseEntity<ReviewResponseDto> updateReview(
//            @PathVariable Long reviewId,
//            @RequestBody ReviewRequestDto reviewRequestDto,
//            Principal principal) {
//        String username = principal.getName(); // Get logged-in user
//        return ResponseEntity.ok(reviewService.updateReview(username, reviewId, reviewRequestDto));
//    }
//
//    // ✅ Delete a Review (Owner or Admin)
//    @DeleteMapping("/{reviewId}")
//    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId, Principal principal) {
//        String username = principal.getName(); // Get logged-in user
//        reviewService.deleteReview(username, reviewId);
//        return ResponseEntity.ok("Review deleted successfully");
//    }
}
