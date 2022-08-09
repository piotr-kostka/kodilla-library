package com.crud.kodilla_library.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long userId;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Column(name = "signup_date")
    private LocalDate signupDate;

    @JsonIgnore
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private List<Rental> rentals;
}
