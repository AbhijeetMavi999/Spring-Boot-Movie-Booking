package com.moviebooking.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CinemaDto {

    private Integer cinemaId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String location;

    @NotEmpty
    private String hallNumber;

    @NotEmpty
    private String seatNumber;

    @NotEmpty
    private Float price;

    @NotEmpty
    private String showDate;

    private UserDto userDto;
}
