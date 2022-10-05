package com.moviebooking.dtos;

import com.moviebooking.entities.Cinema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto {

    private Integer userId;

    @NotEmpty
    private String userName;

    @NotEmpty
    @Email(message = "Please provide valid email address", regexp = ".+@.+\\..+")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 30)
    private String password;

}
