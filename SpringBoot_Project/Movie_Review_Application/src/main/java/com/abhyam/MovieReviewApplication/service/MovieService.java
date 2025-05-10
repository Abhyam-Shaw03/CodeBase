package com.abhyam.MovieReviewApplication.service;


import com.abhyam.MovieReviewApplication.dto.MovieRequestDto;
import com.abhyam.MovieReviewApplication.dto.MovieResponseDto;
import com.abhyam.MovieReviewApplication.model.Movie;
import com.abhyam.MovieReviewApplication.repository.MovieRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepository;

    // Add a new movie (Admin Only)
    public MovieResponseDto addMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setGenre(movieRequestDto.getGenre());
        movie.setDescription(movieRequestDto.getDescription());
        movie.setReleaseDate(movieRequestDto.getReleaseDate());

        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponseDto(savedMovie);
    }

    // Get all movies with their average ratings
    public List<MovieResponseDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieResponseDto::new)
                .collect(Collectors.toList());
    }

    // Get a movie by ID
    public MovieResponseDto getMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        return new MovieResponseDto(movie);
    }

    // ✅ Update Movie Details (Admin Only)
    public MovieResponseDto updateMovie(Long movieId, MovieRequestDto movieRequestDto) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setTitle(movieRequestDto.getTitle());
        movie.setGenre(movieRequestDto.getGenre());
        movie.setDescription(movieRequestDto.getDescription());
        movie.setReleaseDate(movieRequestDto.getReleaseDate());

        Movie updatedMovie = movieRepository.save(movie);
        return new MovieResponseDto(updatedMovie);
    }

    // ✅ Delete a Movie (Admin Only)
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        movieRepository.delete(movie);
    }
}
