package com.moviebooking.dtos;

import com.moviebooking.dtos.responseDto.CinemaScreenDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ScreenDto {

    private Integer screenId;
    private String type;

    private CinemaScreenDto cinemaScreenDto;
}
