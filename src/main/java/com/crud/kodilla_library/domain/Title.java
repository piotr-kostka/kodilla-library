package com.crud.kodilla_library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private Set<Exemplar> exemplars;
}
