package com.moviebooking.services;

import com.moviebooking.dtos.ScreenDto;
import com.moviebooking.entities.Screen;
import com.moviebooking.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ScreenService {

    public ScreenDto createScreen(Screen screen, Integer cinemaId) throws ResourceNotFoundException;

    public List<ScreenDto> getByType(String type) throws ResourceNotFoundException;

    public ScreenDto updateScreen(Screen screen, Integer screenId) throws ResourceNotFoundException;

    public void deleteScreen(Integer screenId) throws ResourceNotFoundException;
}
