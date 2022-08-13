package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.RentalNotFoundException;
import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.ExemplarStatus;
import com.crud.kodilla_library.domain.Rental;
import com.crud.kodilla_library.domain.dto.RentalDto;
import com.crud.kodilla_library.mapper.RentalMapper;
import com.crud.kodilla_library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalDbService {

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public List<RentalDto> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentalMapper.mapToRentalDtoList(rentals);
    }

    public RentalDto getRental(final long rentalId) throws RentalNotFoundException {
        Rental rental = rentalRepository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        return rentalMapper.mapToRentalDto(rental);
    }

    public List<RentalDto> getUserRentals(final long userId) {
        List<Rental> userRentals = rentalRepository.findAll().stream()
                .filter(r -> r.getUser().getUserId() == userId)
                .collect(Collectors.toList());
        return rentalMapper.mapToRentalDtoList(userRentals);
    }

    @Transactional
    public RentalDto createRental(final RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Exemplar exemplar = rental.getExemplar();
        exemplar.setStatus(ExemplarStatus.RENTED);
        rental.setRentDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(30));
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.mapToRentalDto(savedRental);
    }

    @Transactional
    public void returnExemplar(final long rentalId) {
        Optional<Rental> rental = rentalRepository.findById(rentalId);

        if(rental.isPresent()) {
            Exemplar exemplar =rental.get().getExemplar();
            exemplar.setStatus(ExemplarStatus.AVAILABLE);
        }

        rentalRepository.deleteById(rentalId);
    }
}
