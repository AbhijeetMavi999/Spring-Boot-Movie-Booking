package com.moviebooking.services.impl;

import com.moviebooking.dtos.ScreenDto;
import com.moviebooking.entities.Cinema;
import com.moviebooking.entities.Screen;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repositories.CinemaRepository;
import com.moviebooking.repositories.ScreenRepository;
import com.moviebooking.services.ScreenService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ScreenServiceImpl implements ScreenService {

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${screen-not-found}")
    private String screenNotFound;

    @Value("${cinema-not-found}")
    private String cinemaNotFound;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ScreenDto createScreen(Screen screen, Integer cinemaId) throws ResourceNotFoundException {
        Cinema cinema = cinemaRepository.findByCinemaId(cinemaId);
        if(cinema == null) {
            log.error(cinemaNotFound);
            throw new ResourceNotFoundException(cinemaNotFound);
        }
        screen.setCinemaDto(cinema);
        Screen createScreen = screenRepository.save(screen);
        return modelMapper.map(createScreen, ScreenDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<ScreenDto> getByType(String type) throws ResourceNotFoundException {
        List<Screen> screens = screenRepository.findByType(type);
        if(screens == null) {
            log.error(screenNotFound);
            throw new ResourceNotFoundException(screenNotFound);
        }
        List<ScreenDto> screenDtos = screens.stream().map(screen -> modelMapper.map(screen, ScreenDto.class)).collect(Collectors.toList());
        return screenDtos;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ScreenDto updateScreen(Screen screen, Integer screenId) throws ResourceNotFoundException {
        Screen updateScreen = screenRepository.findById(screenId).get();
        if(updateScreen == null) {
            log.error(screenNotFound);
            throw new ResourceNotFoundException(screenNotFound);
        }
        updateScreen.setType(screen.getType());
        Screen updatedScreen = screenRepository.save(updateScreen);
        return modelMapper.map(updatedScreen, ScreenDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteScreen(Integer screenId) throws ResourceNotFoundException {
        Screen screen = screenRepository.findById(screenId).get();
        if(screen == null) {
            log.error(screenNotFound);
            throw new ResourceNotFoundException(screenNotFound);
        }
        screenRepository.deleteById(screenId);
    }
}
