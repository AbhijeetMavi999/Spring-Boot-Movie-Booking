package com.moviebooking.services;

import com.moviebooking.dtos.CinemaDto;
import com.moviebooking.entities.Inox;
import com.moviebooking.entities.PVR;
import com.moviebooking.exceptions.ResourceNotFoundException;

import java.util.List;

public interface CinemaService {

    public CinemaDto createPVR(PVR pvr, Integer userId) throws ResourceNotFoundException;

    public CinemaDto createInox(Inox inox, Integer userId) throws ResourceNotFoundException;

    public List<CinemaDto> getAllCinemas();

    public List<CinemaDto> getCinemaByName(String name) throws ResourceNotFoundException;

    public List<CinemaDto> getAllCinemaOrderByPrice() throws ResourceNotFoundException;

    public List<CinemaDto> getAllByLocation(String location) throws ResourceNotFoundException;

    public List<CinemaDto> getCinemasByNameAndLocation(String name, String location) throws ResourceNotFoundException;

    public void deleteCinema(Integer cinemaId) throws ResourceNotFoundException;
}
