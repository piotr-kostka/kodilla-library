package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.RentalNotFoundException;
import com.crud.kodilla_library.domain.*;
import com.crud.kodilla_library.domain.dto.RentalDto;
import com.crud.kodilla_library.mapper.RentalMapper;
import com.crud.kodilla_library.repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalDbServiceTest {

    @InjectMocks
    private RentalDbService rentalDbService;

    @Mock
    private RentalMapper rentalMapperMock;

    @Mock
    private RentalRepository rentalRepositoryMock;

    private User user;
    private Title title;
    private Exemplar exemplar;
    private Rental rental;
    private RentalDto rentalDto;
    private List<Rental> rentalList = new ArrayList<>();
    private List<RentalDto> rentalDtoList = new ArrayList<>();

    @BeforeEach
    void init() {
        user = new User(1L, "firstname", "lastname", LocalDate.of(2022, 8, 14), new HashSet<>());
        title = new Title(1L, "title", "author", 2022, new HashSet<>());
        exemplar = new Exemplar(1L, title, ExemplarStatus.AVAILABLE, new HashSet<>());
        rental = new Rental(1L, exemplar, user, LocalDate.of(2022, 8, 14), LocalDate.of(2022, 8, 14).plusDays(30));
        rentalDto = new RentalDto(1L, exemplar, user, LocalDate.of(2022, 8, 14), LocalDate.of(2022, 8, 14).plusDays(30));
        rentalList.add(rental);
        rentalDtoList.add(rentalDto);
    }

    @Test
    void getAllRentalsTest() {
        //Given
        when(rentalMapperMock.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);
        when(rentalRepositoryMock.findAll()).thenReturn(rentalList);

        //When
        List<RentalDto> expectedList = rentalDbService.getAllRentals();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getRentId());
        assertEquals("title", expectedList.get(0).getExemplar().getTitle().getTitle());
        assertEquals(ExemplarStatus.AVAILABLE, expectedList.get(0).getExemplar().getStatus());
        assertEquals("firstname", expectedList.get(0).getUser().getFirstname());
        assertEquals(LocalDate.of(2022,8,14), expectedList.get(0).getRentDate());
    }

    @Test
    void getRentalTest() throws RentalNotFoundException {
        //Given
        when(rentalMapperMock.mapToRentalDto(rental)).thenReturn(rentalDto);
        when(rentalRepositoryMock.findById(rentalDto.getRentId())).thenReturn(Optional.of(rental));

        //When
        RentalDto expectedRentalDto = rentalDbService.getRental(1);

        //Then
        assertEquals(1L, expectedRentalDto.getRentId());
        assertEquals("title", expectedRentalDto.getExemplar().getTitle().getTitle());
        assertEquals(ExemplarStatus.AVAILABLE, expectedRentalDto.getExemplar().getStatus());
        assertEquals("firstname", expectedRentalDto.getUser().getFirstname());
        assertEquals(LocalDate.of(2022,8,14), expectedRentalDto.getRentDate());
    }

    @Test
    void getUserRentalsTest() {
        //Given
        when(rentalMapperMock.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);
        when(rentalRepositoryMock.findAll()).thenReturn(rentalList);

        //When
        List<RentalDto> expectedList = rentalDbService.getUserRentals(1L);

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void createRentalTest() {
        //Given
        when(rentalMapperMock.mapToRental(rentalDto)).thenReturn(rental);
        Rental savedRental = rentalMapperMock.mapToRental(rentalDto);
        when(rentalRepositoryMock.save(rental)).thenReturn(savedRental);
        when(rentalMapperMock.mapToRentalDto(savedRental)).thenReturn(rentalDto);

        //When
        RentalDto expectedRentalDto = rentalDbService.createRental(rentalDto);

        //Then
        assertEquals(1L, expectedRentalDto.getRentId());
        assertEquals("title", expectedRentalDto.getExemplar().getTitle().getTitle());
        assertEquals(2022, expectedRentalDto.getExemplar().getTitle().getPublicationYear());
        assertEquals("firstname", expectedRentalDto.getUser().getFirstname());
        assertEquals(LocalDate.of(2022,8,14), expectedRentalDto.getRentDate());
        assertEquals(LocalDate.of(2022,9,13), expectedRentalDto.getReturnDate());
    }

    @Test
    void returnExemplarTest() {
        //When
        rentalDbService.returnExemplar(1L);
        //Then
        verify(rentalRepositoryMock,times(1)).deleteById(1L);
    }
}