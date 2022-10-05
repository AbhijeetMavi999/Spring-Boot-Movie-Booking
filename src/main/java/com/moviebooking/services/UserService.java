package com.moviebooking.services;

import com.moviebooking.dtos.UserDto;
import com.moviebooking.exceptions.ResourceAlreadyTakenException;
import com.moviebooking.exceptions.ResourceNotFoundException;

public interface UserService {

    public void registerUser(UserDto userDto) throws ResourceAlreadyTakenException;

    public UserDto getUserById(Integer userId) throws ResourceNotFoundException;

    public UserDto getUserByUserName(String userName) throws ResourceNotFoundException;

    public UserDto updateUser(Integer userId, UserDto userDto) throws ResourceNotFoundException;

    public void deleteUserById(Integer userId) throws ResourceNotFoundException;
}
