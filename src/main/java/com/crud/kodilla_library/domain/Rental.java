package com.crud.kodilla_library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "rented_books")
public class Rental {

    @Id
    @GeneratedValue
    @Column(name = "rent_id")
    private long rentId;

    @ManyToOne
    @JoinColumn(name = "EXEMPLAR")
    private Exemplar exemplar;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @NotNull
    @Column(name = "rent_date")
    private LocalDate rentDate;

    @NotNull
    @Column(name = "return_date")
    private LocalDate returnDate;
}