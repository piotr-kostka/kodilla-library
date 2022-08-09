package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.RentalNotFoundException;
import com.crud.kodilla_library.domain.Rental;
import com.crud.kodilla_library.domain.dto.RentalDto;
import com.crud.kodilla_library.mapper.RentalMapper;
import com.crud.kodilla_library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/library/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final DbService service;
    private final RentalMapper rentalMapper;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals() {
        List<Rental> rentals = service.getAllRentals();
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(rentals));
    }

    @GetMapping(value = "{rentalId}")
    public ResponseEntity<RentalDto> getRental(@PathVariable long rentalId) throws RentalNotFoundException {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(service.getRental(rentalId)));
    }

    @GetMapping(value = "/getUserRentals/{userId}")
    public ResponseEntity<List<RentalDto>> getUserRentals(@PathVariable long userId) {
        List<Rental> userRentals = service.getUserRentals(userId);
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(userRentals));
    }

    @DeleteMapping(value = "{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable long rentalId) {
        service.deleteRental(rentalId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Rental savedRental = service.saveRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(savedRental));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> createRental(@RequestBody RentalDto rentalDto) {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        rental.setRentDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(30));
        Rental createdRental = service.saveRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(createdRental));
    }
}
