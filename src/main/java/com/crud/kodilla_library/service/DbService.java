package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.ExemplarNotFoundException;
import com.crud.kodilla_library.controller.exceptions.RentalNotFoundException;
import com.crud.kodilla_library.controller.exceptions.TitleNotFoundException;
import com.crud.kodilla_library.controller.exceptions.UserNotFoundException;
import com.crud.kodilla_library.domain.*;
import com.crud.kodilla_library.repository.ExemplarRepository;
import com.crud.kodilla_library.repository.RentalRepository;
import com.crud.kodilla_library.repository.TitleRepository;
import com.crud.kodilla_library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;
    private final TitleRepository titleRepository;
    private final ExemplarRepository exemplarRepository;
    private final RentalRepository rentalRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(final long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitle(final long titleId) throws TitleNotFoundException {
        return titleRepository.findById(titleId).orElseThrow(TitleNotFoundException::new);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public void deleteTitle(final Long titleId) {
        titleRepository.deleteById(titleId);
    }

    public List<Exemplar> getAllExemplars() {
        return exemplarRepository.findAll();
    }

    public Exemplar getExemplar(final long exemplarId) throws ExemplarNotFoundException {
        return exemplarRepository.findById(exemplarId).orElseThrow(ExemplarNotFoundException::new);
    }

    public List<Exemplar> getAvailableExemplars() {
        List<Exemplar> availableExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.AVAILABLE))
                .collect(Collectors.toList());
        return availableExemplars;
    }

    public List<Exemplar> getRentedExemplars() {
        List<Exemplar> rentedExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.RENTED))
                .collect(Collectors.toList());
        return rentedExemplars;
    }

    public List<Exemplar> getLostExemplars() {
        List<Exemplar> rentedExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.LOST))
                .collect(Collectors.toList());
        return rentedExemplars;
    }

    public List<Exemplar> getDestroyedExemplars() {
        List<Exemplar> rentedExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.DESTROYED))
                .collect(Collectors.toList());
        return rentedExemplars;
    }

    public Exemplar saveExemplar(final Exemplar exemplar) {
        return exemplarRepository.save(exemplar);
    }

    public void deleteExemplar(final long exemplarId) {
        exemplarRepository.deleteById(exemplarId);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRental(final long rentalId) throws RentalNotFoundException {
        return rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
    }

    public List<Rental> getUserRentals(final long userId) {
        return rentalRepository.findAll().stream()
                .filter(r -> r.getUser().getUserId() == userId)
                .collect(Collectors.toList());
    }

    public Rental saveRental(final Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRental(final long rentalId) {
        rentalRepository.deleteById(rentalId);
    }
}
