package com.crud.kodilla_library.domain.dto;

import com.crud.kodilla_library.domain.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private long userId;
    private String firstname;
    private String lastname;
    private LocalDate signupDate;

    @JsonIgnore
    private Set<Rental> rentals;
}
