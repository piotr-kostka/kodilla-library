package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.*;
import com.crud.kodilla_library.domain.dto.RentalDto;
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

    public User initUser() {
        return new User(1L, "firstname", "lastname", LocalDate.of(2022,8,14),new HashSet<>());
    }

    public Title initTitle() {
        return new Title(1L, "title", "author", 2022, new HashSet<>());
    }

    public Exemplar initExemplar() {
        return new Exemplar(1L, initTitle(), ExemplarStatus.AVAILABLE, new HashSet<>());
    }

    @Test
    void mapToRentalTest() {
        //Given
        RentalDto rentalDto = new RentalDto(1L, initExemplar(), initUser(), LocalDate.of(2022,8,14), LocalDate.of(2022,8,14).plusDays(30));
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
        //Given
        Rental rental = new Rental(1L, initExemplar(), initUser(), LocalDate.of(2022,8,14), LocalDate.of(2022,8,14).plusDays(30));
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
        //Given
        Rental rental = new Rental(1L, initExemplar(), initUser(), LocalDate.of(2022,8,14), LocalDate.of(2022,8,14).plusDays(30));
        List<Rental> list = new ArrayList<>();
        list.add(rental);
        //When
        List<RentalDto> expected = rentalMapper.mapToRentalDtoList(list);
        //Then
        assertEquals(1, expected.size());
    }
}