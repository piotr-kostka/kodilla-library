package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.RentalNotFoundException;
import com.crud.kodilla_library.domain.dto.RentalDto;
import com.crud.kodilla_library.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalDbService rentalDbService;

    @GetMapping
    public List<RentalDto> getRentals() {
        return rentalDbService.getAllRentals();
    }

    @GetMapping(value = "{rentalId}")
    public RentalDto getRental(@PathVariable long rentalId) throws RentalNotFoundException {
        return rentalDbService.getRental(rentalId);
    }

    @GetMapping(value = "/getUserRentals/{userId}")
    public List<RentalDto> getUserRentals(@PathVariable long userId) {
        return rentalDbService.getUserRentals(userId);
    }

    @DeleteMapping(value = "{rentalId}")
    public void deleteRental(@PathVariable long rentalId) {
        rentalDbService.returnExemplar(rentalId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto updateRental(@RequestBody RentalDto rentalDto) {
        return rentalDbService.createRental(rentalDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public RentalDto createRental(@RequestBody RentalDto rentalDto) {
        return rentalDbService.createRental(rentalDto);
    }
}
