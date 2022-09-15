package com.crud.kodilla_library.domain.dto;

import com.crud.kodilla_library.domain.ExemplarStatus;
import com.crud.kodilla_library.domain.Rental;
import com.crud.kodilla_library.domain.Title;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExemplarDto {

    private long exemplarId;
    private Title title;
    private ExemplarStatus status;

    @JsonIgnore
    private Set<Rental> rentals;
}
