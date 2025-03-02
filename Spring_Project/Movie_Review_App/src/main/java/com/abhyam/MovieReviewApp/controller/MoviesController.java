package com.abhyam.MovieReviewApp.controller;

import com.abhyam.MovieReviewApp.model.Movies;
import com.abhyam.MovieReviewApp.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService service;

    @GetMapping
    public ResponseEntity<List<Movies>> getAllMovies(){
        return new ResponseEntity<>(service.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movies> getMovieById(@PathVariable long movieId){
        Movies movie = service.getMovieById(movieId);
        if (movie != null) {
            System.out.println("Movie Found: " + movie);
            return new ResponseEntity<>(movie, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Movies> addMovie(@RequestBody Movies movie) {
        Movies newMovie = service.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<String> updateMovie(@PathVariable long movieId, @RequestBody Movies movie) {
        Movies mov = service.updateMovie(movieId, movie);
        if (mov != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable long movieId){
        Movies movie = service.getMovieById(movieId);
        if(movie != null){
            service.deleteMovie(movieId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }
}
