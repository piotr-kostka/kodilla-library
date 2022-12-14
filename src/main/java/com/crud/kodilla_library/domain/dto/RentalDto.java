package com.crud.kodilla_library.domain.dto;

import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalDto {

    private long rentId;
    private Exemplar exemplar;
    private User user;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
