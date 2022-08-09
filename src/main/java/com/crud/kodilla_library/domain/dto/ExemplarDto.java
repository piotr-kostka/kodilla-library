package com.crud.kodilla_library.domain.dto;

import com.crud.kodilla_library.domain.ExemplarStatus;
import com.crud.kodilla_library.domain.Rental;
import com.crud.kodilla_library.domain.Title;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExemplarDto {

    private long exemplarId;
    private Title title;
    private ExemplarStatus status;

    @JsonIgnore
    private List<Rental> rentals;
}
