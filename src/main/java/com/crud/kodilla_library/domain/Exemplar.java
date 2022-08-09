package com.crud.kodilla_library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "exemplars")
public class Exemplar {

    @Id
    @GeneratedValue
    @Column(name = "exemplar_id")
    private long exemplarId;

    @ManyToOne
    @JoinColumn(name = "TITLE")
    private Title titleId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExemplarStatus status;

    @JsonIgnore
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "exemplar",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Rental> rentals = new ArrayList<>();
}
