package com.moviebooking.services;

import com.moviebooking.dtos.MovieDto;
import com.moviebooking.entities.Movie;
import com.moviebooking.exceptions.ResourceNotFoundException;

public interface MovieService {

    public MovieDto registerMovie(Movie movie, Integer screenId) throws ResourceNotFoundException;

    public MovieDto getMovieByTitle(String title) throws ResourceNotFoundException;

    public MovieDto getMovieById(Integer movieId) throws ResourceNotFoundException;

}
