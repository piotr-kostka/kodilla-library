package com.crud.kodilla_library.domain.dto;

import com.crud.kodilla_library.domain.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long userId;
    private String firstname;
    private String lastname;
    private LocalDate signupDate;

    @JsonIgnore
    private List<Rental> rentals;
}
