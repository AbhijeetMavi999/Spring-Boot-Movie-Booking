package com.moviebooking.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MovieDto {

    private Integer movieId;
    private String title;
    private String releaseDate;
    private String showCycle;

    private ScreenDto screenDto;
}
