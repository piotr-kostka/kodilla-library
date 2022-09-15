package com.crud.kodilla_library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "rented_books")
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "rent_id")
    private long rentId;

    @ManyToOne
    @JoinColumn(name = "exemplar")
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @NotNull
    @Column(name = "rent_date")
    private LocalDate rentDate;

    @NotNull
    @Column(name = "return_date")
    private LocalDate returnDate;
}