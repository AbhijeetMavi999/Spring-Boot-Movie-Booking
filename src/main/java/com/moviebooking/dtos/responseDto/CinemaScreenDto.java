package com.moviebooking.dtos.responseDto;

import com.moviebooking.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CinemaScreenDto {

    private Integer cinemaId;
    private String name;
    private String location;

    private UserDto userDto;
}
