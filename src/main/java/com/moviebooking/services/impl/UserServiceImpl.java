package com.moviebooking.services.impl;

import com.moviebooking.dtos.UserDto;
import com.moviebooking.entities.User;
import com.moviebooking.exceptions.ResourceAlreadyTakenException;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.repositories.UserRepository;
import com.moviebooking.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${user-not-found}")
    private String userNotFound;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void registerUser(UserDto userDto) throws ResourceAlreadyTakenException {
        User checkUser = userRepository.findByEmail(userDto.getEmail());
        if(checkUser != null) {
            log.error("Email already Taken");
            throw new ResourceAlreadyTakenException("Email already Taken");
        }
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDto getUserById(Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            log.error(userNotFound," by Id {}", userId);
            throw new ResourceNotFoundException(userNotFound);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDto getUserByUserName(String userName) throws ResourceNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user == null) {
            log.error(userNotFound," by username {}", userName);
            throw new ResourceNotFoundException(userNotFound);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) throws ResourceNotFoundException {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            log.error(userNotFound," by Id {}", userId);
            throw new ResourceNotFoundException(userNotFound);
        }
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User updateUser = userRepository.save(user);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserById(Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findByUserId(userId);
        if(user == null) {
            log.error(userNotFound," by Id {}",userId);
            throw new ResourceNotFoundException(userNotFound);
        }
        userRepository.deleteByUserId(userId);
    }
}
