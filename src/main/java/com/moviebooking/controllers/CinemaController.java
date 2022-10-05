package com.moviebooking.controllers;

import com.moviebooking.dtos.CinemaDto;
import com.moviebooking.dtos.responseDto.ResponseDto;
import com.moviebooking.entities.Inox;
import com.moviebooking.entities.PVR;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.services.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/create/pvr/user/{userId}")
    public ResponseEntity<CinemaDto> createPVR(@RequestBody PVR pvr, @PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        CinemaDto createdPVR = cinemaService.createPVR(pvr, userId);
        return new ResponseEntity<>(createdPVR, HttpStatus.CREATED);
    }

    @PostMapping("/create/inox/user/{userId}")
    public ResponseEntity<CinemaDto> createInox(@RequestBody Inox inox, @PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        CinemaDto createdInox = cinemaService.createInox(inox, userId);
        return new ResponseEntity<>(createdInox, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CinemaDto>> getAllCinemas() {
        List<CinemaDto> cinemaDto = cinemaService.getAllCinemas();
        return new ResponseEntity<List<CinemaDto>>(cinemaDto, HttpStatus.FOUND);
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<List<CinemaDto>> getCinemasByName(@PathVariable("name") String name) throws ResourceNotFoundException {
        List<CinemaDto> cinemaDtos = cinemaService.getCinemaByName(name);
        return new ResponseEntity<List<CinemaDto>>(cinemaDtos, HttpStatus.FOUND);
    }

    @GetMapping("/getbyprice")
    public ResponseEntity<List<CinemaDto>> getAllCinemaOrderByPrice() throws ResourceNotFoundException {
        List<CinemaDto> cinemaDtos = cinemaService.getAllCinemaOrderByPrice();
        return new ResponseEntity<List<CinemaDto>>(cinemaDtos, HttpStatus.FOUND);
    }

    @GetMapping("/get/location/{location}")
    public ResponseEntity<List<CinemaDto>> getAllByLocation(@PathVariable("location") String location) throws ResourceNotFoundException {
        List<CinemaDto> cinemaDtos = cinemaService.getAllByLocation(location);
        return new ResponseEntity<List<CinemaDto>>(cinemaDtos, HttpStatus.FOUND);
    }

    @GetMapping("/getby/name/{name}/location/{location}")
    public ResponseEntity<List<CinemaDto>> getCinemasByNameAndLocation(@PathVariable("name") String name, @PathVariable("location") String location) throws ResourceNotFoundException {
        List<CinemaDto> cinemaDtos = cinemaService.getCinemasByNameAndLocation(name, location);
        return new ResponseEntity<>(cinemaDtos, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{cinemaId}")
    public ResponseEntity<ResponseDto> deleteCinema(@PathVariable("cinemaId") Integer cinemaId) throws ResourceNotFoundException {
        cinemaService.deleteCinema(cinemaId);
        return new ResponseEntity<>(new ResponseDto("Cinema successfully deleted", true), HttpStatus.OK);
    }
}
