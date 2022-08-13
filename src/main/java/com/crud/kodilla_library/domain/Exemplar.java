package com.crud.kodilla_library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "exemplars")
public class Exemplar {

    @Id
    @GeneratedValue
    @Column(name = "exemplar_id")
    private long exemplarId;

    @ManyToOne
    @JoinColumn(name = "title")
    private Title title;

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
    private Set<Rental> rentals;
}
