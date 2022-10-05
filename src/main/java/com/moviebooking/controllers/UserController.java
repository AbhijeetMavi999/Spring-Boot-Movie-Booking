package com.moviebooking.controllers;

import com.moviebooking.dtos.UserDto;
import com.moviebooking.dtos.responseDto.ResponseDto;
import com.moviebooking.exceptions.ResourceAlreadyTakenException;
import com.moviebooking.exceptions.ResourceNotFoundException;
import com.moviebooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserDto userDto) throws ResourceAlreadyTakenException {
        userService.registerUser(userDto);
        return new ResponseEntity<>(new ResponseDto("User successfully registered", true), HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/getbyusername/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable("userName") String userName) throws ResourceNotFoundException {
        UserDto userDto = userService.getUserByUserName(userName);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDto userDto) throws ResourceNotFoundException {
        UserDto updatedUser = userService.updateUser(userId, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("userId") Integer userId) throws ResourceNotFoundException {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(new ResponseDto("User successfully deleted", true), HttpStatus.OK);
    }
}





















