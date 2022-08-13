package com.crud.kodilla_library.domain.dto;

import com.crud.kodilla_library.domain.Exemplar;
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
public class TitleDto {

    private long titleId;
    private String title;
    private String author;
    private int publicationYear;

    @JsonIgnore
    private Set<Exemplar> exemplars;
}
