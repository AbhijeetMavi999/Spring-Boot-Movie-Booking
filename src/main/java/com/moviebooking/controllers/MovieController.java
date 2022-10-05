package com.moviebooking.controllers;

import com.moviebooking.dtos.MovieDto;
import com.moviebooking.dtos.responseDto.ResponseDto;
import com.moviebooking.entities.Movie;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create/screen/{screenId}")
    public ResponseEntity<MovieDto> registerMovie(@RequestBody Movie movie, @PathVariable("screenId") Integer screenId) throws ResourceNotFoundException {
        MovieDto movieDto = movieService.registerMovie(movie, screenId);
        return new ResponseEntity<>(movieDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/title/{title}")
    public ResponseEntity<MovieDto> getMovieByTitle(@PathVariable("title") String title) throws ResourceNotFoundException {
        MovieDto movieDto = movieService.getMovieByTitle(title);
        return new ResponseEntity<>(movieDto, HttpStatus.FOUND);
    }

    @GetMapping("/get/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable("movieId") Integer movieId) throws ResourceNotFoundException {
        MovieDto movieDto = movieService.getMovieById(movieId);
        return new ResponseEntity<>(movieDto, HttpStatus.FOUND);
    }
}
