package com.abhyam.MovieReviewApplication.service;

import com.abhyam.MovieReviewApplication.dto.ReviewRequestDto;
import com.abhyam.MovieReviewApplication.dto.ReviewResponseDto;
import com.abhyam.MovieReviewApplication.model.Movie;
import com.abhyam.MovieReviewApplication.model.Review;
import com.abhyam.MovieReviewApplication.model.Role;
import com.abhyam.MovieReviewApplication.model.User;
import com.abhyam.MovieReviewApplication.repository.MovieRepo;
import com.abhyam.MovieReviewApplication.repository.ReviewRepo;
import com.abhyam.MovieReviewApplication.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo reviewRepository;
    @Autowired
    private MovieRepo movieRepository;
    @Autowired
    private UserRepo userRepository;

    // Add a new review
    @Transactional
    public ReviewResponseDto addReview(String username, ReviewRequestDto reviewRequestDto) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User not found");
        }

        Movie movie = movieRepository.findById(reviewRequestDto.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        // Create review
        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setRating(reviewRequestDto.getRating());
        review.setComment(reviewRequestDto.getComment());

        System.out.println("Saving Review: " + review);

        Review savedReview = reviewRepository.save(review);

        System.out.println("Saving Review: " + savedReview);

        // Update movie's average rating
        updateMovieAverageRating(movie);

        return new ReviewResponseDto(savedReview);
    }

    // Get all reviews for a specific movie
    public List<ReviewResponseDto> getReviewsByMovie(Long movieId) {
        return reviewRepository.findByMovieIdOrderByCreatedAtDesc(movieId)
                .stream()
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    // ✅ Update Review (Only Owner can update)
    @Transactional
    public ReviewResponseDto updateReview(String username, Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Only the owner can update
        if (!review.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You can only update your own reviews!");
        }

        review.setRating(reviewRequestDto.getRating());
        review.setComment(reviewRequestDto.getComment());

        Review updatedReview = reviewRepository.save(review);

        // Update movie's average rating
        updateMovieAverageRating(review.getMovie());

        return new ReviewResponseDto(updatedReview);
    }

    // ✅ Delete Review (Owner or Admin, but Admin cannot delete another Admin's review)
    @Transactional
    public void deleteReview(String username, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        User currentUser = userRepository.findByUsername(username);
        if(currentUser == null){
            throw new RuntimeException("User not found");
        }

        User reviewOwner = review.getUser();

        // User can only delete their own review
        if (currentUser.getRole() == Role.USER && !reviewOwner.getUsername().equals(username)) {
            throw new RuntimeException("You can only delete your own reviews!");
        }

        // Admin can delete any USER review but NOT another ADMIN's review
        if (currentUser.getRole() == Role.ADMIN && reviewOwner.getRole() == Role.ADMIN) {
            throw new RuntimeException("You cannot delete another Admin's review!");
        }

        Movie movie = review.getMovie();
        reviewRepository.delete(review);

        // Update movie's average rating after review deletion
        updateMovieAverageRating(movie);
    }

//    @Transactional
//    public ReviewResponseDto updateReview(String username, Long reviewId, ReviewRequestDto reviewRequestDto) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new RuntimeException("Review not found"));
//
//        if (!review.getUser().getUsername().equals(username)) {
//            throw new RuntimeException("You can only update your own reviews!");
//        }
//
//        review.setRating(reviewRequestDto.getRating());
//        review.setComment(reviewRequestDto.getComment());
//
//        Review updatedReview = reviewRepository.save(review);
//
//        // Update movie's average rating
//        updateMovieAverageRating(review.getMovie());
//
//        return new ReviewResponseDto(updatedReview);
//    }
//
//    // ✅ Delete Review (Owner or Admin can delete)
//    @Transactional
//    public void deleteReview(String username, Long reviewId) {
//        Review review = reviewRepository.findById(reviewId)
//                .orElseThrow(() -> new RuntimeException("Review not found"));
//
//        User user = review.getUser();
//
//        if (!user.getUsername().equals(username) && !user.getRole().name().equals("ADMIN")) {
//            throw new RuntimeException("You are not allowed to delete this review!");
//        }
//
//        Movie movie = review.getMovie();
//        reviewRepository.delete(review);
//
//        // Update movie's average rating after review deletion
//        updateMovieAverageRating(movie);
//    }

    // Helper method to update movie's average rating
    private void updateMovieAverageRating(Movie movie) {
        List<Review> reviews = reviewRepository.findByMovieIdOrderByCreatedAtDesc(movie.getId());

        double avgRating = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        movie.setRating(avgRating);
        movieRepository.save(movie);
    }
}
