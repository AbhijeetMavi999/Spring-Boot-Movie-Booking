package com.moviebooking.services.impl;

import com.moviebooking.dtos.MovieDto;
import com.moviebooking.entities.Movie;
import com.moviebooking.entities.Screen;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repositories.MovieRepository;
import com.moviebooking.repositories.ScreenRepository;
import com.moviebooking.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${movie-not-found}")
    private String movieNotFound;

    @Value("${screen-not-found}")
    private String screenNotFound;

    @Override
    public MovieDto registerMovie(Movie movie, Integer screenId) throws ResourceNotFoundException {
        Screen screen = screenRepository.findByScreenId(screenId);
        if(screen == null) {
            log.error(screenNotFound);
            throw new ResourceNotFoundException(screenNotFound);
        }
        movie.setScreenDto(screen);
        Movie createMovie = movieRepository.save(movie);
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto getMovieByTitle(String title) throws ResourceNotFoundException {
        Movie movie = movieRepository.findByTitle(title);
        if(movie == null) {
            log.error(movieNotFound);
            throw new ResourceNotFoundException(movieNotFound);
        }
        return modelMapper.map(movie, MovieDto.class);
    }

    @Override
    public MovieDto getMovieById(Integer movieId) throws ResourceNotFoundException {
        Movie movie = movieRepository.findByMovieId(movieId);
        if(movie == null) {
            log.error(movieNotFound);
            throw new ResourceNotFoundException(movieNotFound);
        }
        return modelMapper.map(movie, MovieDto.class);
    }
}
