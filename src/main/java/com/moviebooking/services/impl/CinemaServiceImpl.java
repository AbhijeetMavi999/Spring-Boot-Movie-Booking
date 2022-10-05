package com.moviebooking.services.impl;

import com.moviebooking.dtos.CinemaDto;
import com.moviebooking.entities.Cinema;
import com.moviebooking.entities.Inox;
import com.moviebooking.entities.PVR;
import com.moviebooking.entities.User;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repositories.CinemaRepository;
import com.moviebooking.repositories.UserRepository;
import com.moviebooking.services.CinemaService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${cinema-not-found}")
    private String cinemaNotFound;

    @Value("${user-not-found}")
    private String userNotFound;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CinemaDto createPVR(PVR pvr, Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findByUserId(userId);

        if(user == null) {
            log.error(userNotFound);
            throw new ResourceNotFoundException(userNotFound);
        }
        pvr.setUserDto(user);
        PVR createdPVR = cinemaRepository.save(pvr);
        return modelMapper.map(createdPVR, CinemaDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CinemaDto createInox(Inox inox, Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            log.error(userNotFound);
            throw new ResourceNotFoundException(userNotFound);
        }
        inox.setUserDto(user);
        Inox createdInox = cinemaRepository.save(inox);
        return modelMapper.map(createdInox, CinemaDto.class);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<CinemaDto> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<CinemaDto> cinemaDtos = cinemas.stream().map(cinema -> modelMapper.map(cinema, CinemaDto.class)).collect(Collectors.toList());
        return cinemaDtos;
    }

    @Override
    public List<CinemaDto> getCinemaByName(String name) throws ResourceNotFoundException {
        List<Cinema> cinemas = cinemaRepository.findByName(name);
        if(cinemas.isEmpty()) {
            log.error(cinemaNotFound," by name {}",name);
            throw new ResourceNotFoundException(cinemaNotFound);
        }
        List<CinemaDto> cinemaDtos = cinemas.stream().map(cinema -> modelMapper.map(cinema, CinemaDto.class)).collect(Collectors.toList());
        return cinemaDtos;
    }

    @Override
    public List<CinemaDto> getAllCinemaOrderByPrice() throws ResourceNotFoundException {
        List<Cinema> cinemas = cinemaRepository.getAllCinemaOrderByPrice();
        if(cinemas.isEmpty()) {
            log.error(cinemaNotFound);
            throw new ResourceNotFoundException(cinemaNotFound);
        }
        List<CinemaDto> cinemaDtos = cinemas.stream().map(cinema -> modelMapper.map(cinema, CinemaDto.class)).collect(Collectors.toList());
        return cinemaDtos;
    }

    @Override
    public List<CinemaDto> getAllByLocation(String location) throws ResourceNotFoundException {
        List<Cinema> cinemas = cinemaRepository.findByLocation(location);
        if(cinemas.isEmpty()) {
            log.error(cinemaNotFound);
            throw new ResourceNotFoundException(cinemaNotFound);
        }
        List<CinemaDto> cinemaDtos = cinemas.stream().map(cinema -> modelMapper.map(cinema, CinemaDto.class)).collect(Collectors.toList());
        return cinemaDtos;
    }

    @Override
    public List<CinemaDto> getCinemasByNameAndLocation(String name, String location) throws ResourceNotFoundException {
        List<Cinema> cinemas = cinemaRepository.findByNameAndLocation(name, location);
        if(cinemas.isEmpty()) {
            log.error(cinemaNotFound);
            throw new ResourceNotFoundException(cinemaNotFound);
        }
        List<CinemaDto> cinemaDtos = cinemas.stream().map(cinema -> modelMapper.map(cinema, CinemaDto.class)).collect(Collectors.toList());
        return cinemaDtos;
    }

    @Override
    public void deleteCinema(Integer cinemaId) throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findByCinemaId(cinemaId);
        if(cinema == null) {
            log.error(cinemaNotFound);
            throw new ResourceNotFoundException(cinemaNotFound);
        }
        cinemaRepository.deleteById(cinemaId);
    }
}
