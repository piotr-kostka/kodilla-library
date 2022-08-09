package com.crud.kodilla_library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "titles")
public class Title {

    @Id
    @GeneratedValue
    @Column(name = "title_id")
    private long titleId;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @NotNull
    @Column(name = "publication_year")
    private int publicationYear;

    @JsonIgnore
    @OneToMany(
            targetEntity = Exemplar.class,
            mappedBy = "titleId",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Exemplar> exemplars;
}
