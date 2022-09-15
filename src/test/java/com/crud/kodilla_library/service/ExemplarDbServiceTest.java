package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.ExemplarNotFoundException;
import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.ExemplarStatus;
import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.ExemplarDto;
import com.crud.kodilla_library.mapper.ExemplarMapper;
import com.crud.kodilla_library.repository.ExemplarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExemplarDbServiceTest {

    @InjectMocks
    private ExemplarDbService exemplarDbService;

    @Mock
    private ExemplarMapper exemplarMapperMock;

    @Mock
    private ExemplarRepository exemplarRepositoryMock;

    private Title title;
    private Exemplar exemplar;
    private ExemplarDto exemplarDto;
    private List<Exemplar> exemplarList = new ArrayList<>();
    private List<ExemplarDto> exemplarDtoList = new ArrayList<>();

    @BeforeEach
    void init() {
        title = new Title(1L, "title", "author", 2022, new HashSet<>());
        exemplar = new Exemplar(1L, title, ExemplarStatus.AVAILABLE, new HashSet<>());
        exemplarDto = new ExemplarDto(1L, title, ExemplarStatus.AVAILABLE, new HashSet<>());
        exemplarList.add(exemplar);
        exemplarDtoList.add(exemplarDto);
    }

    @Test
    void getAllExemplarsTest() {
        //Given
        when(exemplarMapperMock.mapToExemplarDtoList(exemplarList)).thenReturn(exemplarDtoList);
        when(exemplarRepositoryMock.findAll()).thenReturn(exemplarList);

        //When
        List<ExemplarDto> expectedList = exemplarDbService.getAllExemplars();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals(1L, expectedList.get(0).getExemplarId());
        assertEquals("title", expectedList.get(0).getTitle().getTitle());
        assertEquals(ExemplarStatus.AVAILABLE, expectedList.get(0).getStatus());
    }

    @Test
    void getExemplarTest() throws ExemplarNotFoundException {
        //Given
        when(exemplarMapperMock.mapToExemplarDto(exemplar)).thenReturn(exemplarDto);
        when(exemplarRepositoryMock.findById(exemplarDto.getExemplarId())).thenReturn(Optional.of(exemplar));

        //When
        ExemplarDto expectedExemplarDto = exemplarDbService.getExemplar(1);

        //Then
        assertEquals("title", expectedExemplarDto.getTitle().getTitle());
        assertEquals(ExemplarStatus.AVAILABLE, expectedExemplarDto.getStatus());
    }

    @Test
    void getAvailableExemplarsTest() {
        //Given
        when(exemplarMapperMock.mapToExemplarDtoList(exemplarList)).thenReturn(exemplarDtoList);
        when(exemplarRepositoryMock.findAll()).thenReturn(exemplarList);

        //When
        List<ExemplarDto> expectedList = exemplarDbService.getAvailableExemplars();

        //Then
        assertEquals(1, expectedList.size());
    }

    @Test
    void getRentedExemplarsTest() {
        //Given
        when(exemplarMapperMock.mapToExemplarDtoList(exemplarList)).thenReturn(new ArrayList<>());
        when(exemplarRepositoryMock.findAll()).thenReturn(exemplarList);

        //When
        List<ExemplarDto> expectedList = exemplarDbService.getAvailableExemplars();

        //Then
        assertTrue(expectedList.isEmpty());
    }

    @Test
    void getLostExemplarsTest() {
        //Given
        when(exemplarMapperMock.mapToExemplarDtoList(exemplarList)).thenReturn(new ArrayList<>());
        when(exemplarRepositoryMock.findAll()).thenReturn(exemplarList);

        //When
        List<ExemplarDto> expectedList = exemplarDbService.getAvailableExemplars();

        //Then
        assertTrue(expectedList.isEmpty());
    }

    @Test
    void getDestroyedExemplarsTest() {
        //Given
        when(exemplarMapperMock.mapToExemplarDtoList(exemplarList)).thenReturn(new ArrayList<>());
        when(exemplarRepositoryMock.findAll()).thenReturn(exemplarList);

        //When
        List<ExemplarDto> expectedList = exemplarDbService.getAvailableExemplars();

        //Then
        assertTrue(expectedList.isEmpty());
    }

    @Test
    void createExemplarTest() {
        //Given
        when(exemplarMapperMock.mapToExemplar(exemplarDto)).thenReturn(exemplar);
        Exemplar savedExemplar = exemplarMapperMock.mapToExemplar(exemplarDto);
        when(exemplarRepositoryMock.save(exemplar)).thenReturn(savedExemplar);
        when(exemplarMapperMock.mapToExemplarDto(savedExemplar)).thenReturn(exemplarDto);

        //When
        ExemplarDto expectedExemplarDto = exemplarDbService.createExemplar(exemplarDto);

        //Then
        assertEquals(1L, expectedExemplarDto.getExemplarId());
        assertEquals("title", expectedExemplarDto.getTitle().getTitle());
        assertEquals(2022, expectedExemplarDto.getTitle().getPublicationYear());
        assertEquals(ExemplarStatus.AVAILABLE, expectedExemplarDto.getStatus());
    }

    @Test
    void deleteExemplarTest() {
        //When
        exemplarDbService.deleteExemplar(1L);
        //Then
        verify(exemplarRepositoryMock, times(1)).deleteById(1L);
    }
}