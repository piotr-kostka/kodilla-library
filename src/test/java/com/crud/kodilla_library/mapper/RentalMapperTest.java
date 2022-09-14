package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.*;
import com.crud.kodilla_library.domain.dto.RentalDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RentalMapperTest {

    @InjectMocks
    private RentalMapper rentalMapper;

    private User user;
    private Title title;
    private Exemplar exemplar;
    private Rental rental;
    private RentalDto rentalDto;
    private List<Rental> rentalList = new ArrayList<>();

    @BeforeEach
    void init() {
        user = new User(1L, "firstname", "lastname", LocalDate.of(2022,8,14),new HashSet<>());
        title = new Title(1L, "title", "author", 2022, new HashSet<>());
        exemplar = new Exemplar(1L, title, ExemplarStatus.AVAILABLE, new HashSet<>());
        rental = new Rental(1L, exemplar, user, LocalDate.of(2022,8,14), LocalDate.of(2022,8,14).plusDays(30));
        rentalDto = new RentalDto(1L, exemplar, user, LocalDate.of(2022,8,14), LocalDate.of(2022,8,14).plusDays(30));
        rentalList.add(rental);
    }

    @Test
    void mapToRentalTest() {
        //When
        Rental expected = rentalMapper.mapToRental(rentalDto);
        //Then
        assertEquals(1L, expected.getRentId());
        assertEquals("title", expected.getExemplar().getTitle().getTitle());
        assertEquals("firstname", expected.getUser().getFirstname());
        assertEquals(LocalDate.of(2022,8,14), expected.getRentDate());
        assertEquals(LocalDate.of(2022, 9,13), expected.getReturnDate());
    }

    @Test
    void mapToRentalDtoTest() {
        //When
        RentalDto expected = rentalMapper.mapToRentalDto(rental);
        //Then
        assertEquals(1L, expected.getRentId());
        assertEquals("title", expected.getExemplar().getTitle().getTitle());
        assertEquals("firstname", expected.getUser().getFirstname());
        assertEquals(LocalDate.of(2022,8,14), expected.getRentDate());
        assertEquals(LocalDate.of(2022, 9,13), expected.getReturnDate());
    }

    @Test
    void mapToRentalDtoListTest() {
        //When
        List<RentalDto> expected = rentalMapper.mapToRentalDtoList(rentalList);
        //Then
        assertEquals(1, expected.size());
    }
}