package com.moviebooking.controllers;

import com.moviebooking.dtos.ScreenDto;
import com.moviebooking.dtos.responseDto.ResponseDto;
import com.moviebooking.entities.Screen;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screen")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping("/create/cinema/{cinemaId}")
    public ResponseEntity<ScreenDto> createScreen(@RequestBody Screen screen, @PathVariable("cinemaId") Integer cinemaId) throws ResourceNotFoundException {
        ScreenDto screenDto = screenService.createScreen(screen, cinemaId);
        return new ResponseEntity<>(screenDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/type/{type}")
    public ResponseEntity<List<ScreenDto>> getScreenByType(@PathVariable("type") String type) throws ResourceNotFoundException {
        List<ScreenDto> screenDtos = screenService.getByType(type);
        return new ResponseEntity<List<ScreenDto>>(screenDtos, HttpStatus.FOUND);
    }

    @PutMapping("/update/{screenId}")
    public ResponseEntity<ScreenDto> updateScreen(@RequestBody Screen screen, @PathVariable("screenId") Integer screenId) throws ResourceNotFoundException {
        ScreenDto updatedScreen = screenService.updateScreen(screen, screenId);
        return new ResponseEntity<>(updatedScreen, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{screenId}")
    public ResponseEntity<ResponseDto> deleteScreen(@PathVariable("screenId") Integer screenId) throws ResourceNotFoundException {
        screenService.deleteScreen(screenId);
        return new ResponseEntity<>(new ResponseDto("Screen successfully deleted", true), HttpStatus.OK);
    }
}
