package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.TitleNotFoundException;
import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.TitleDto;
import com.crud.kodilla_library.mapper.TitleMapper;
import com.crud.kodilla_library.repository.TitleRepository;
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
class TitleDbServiceTest {

    @InjectMocks
    private TitleDbService titleDbService;

    @Mock
    private TitleMapper titleMapperMock;

    @Mock
    private TitleRepository titleRepositoryMock;

    private Title title;
    private TitleDto titleDto;
    private List<Title> titleList = new ArrayList<>();
    private List<TitleDto> titleDtoList = new ArrayList<>();

    @BeforeEach
    void init() {
        title = new Title(1L, "title", "author", 2022, new HashSet<>());
        titleDto = new TitleDto(1L, "title", "author", 2022, new HashSet<>());
        titleList.add(title);
        titleDtoList.add(titleDto);
    }

    @Test
    void getAllTitlesTest() {
        //Given
        when(titleMapperMock.mapToTitleDtoList(titleList)).thenReturn(titleDtoList);
        when(titleRepositoryMock.findAll()).thenReturn(titleList);

        //When
        List<TitleDto> expectedList = titleDbService.getAllTitles();

        //Then
        assertEquals(1, expectedList.size());
        assertEquals("title", expectedList.get(0).getTitle());
        assertEquals("author", expectedList.get(0).getAuthor());
    }

    @Test
    void getTitleTest() throws TitleNotFoundException {
        //Given
        when(titleMapperMock.mapToTitleDto(title)).thenReturn(titleDto);
        when(titleRepositoryMock.findById(titleDto.getTitleId())).thenReturn(Optional.of(title));

        //When
        TitleDto expectedTitleDto = titleDbService.getTitle(1);

        //Then
        assertEquals("title", expectedTitleDto.getTitle());
        assertEquals("author", expectedTitleDto.getAuthor());
    }

    @Test
    void createTitleTest() {
        //Given
        when(titleMapperMock.mapToTitle(titleDto)).thenReturn(title);
        Title savedTitle = titleMapperMock.mapToTitle(titleDto);
        when(titleRepositoryMock.save(title)).thenReturn(savedTitle);
        when(titleMapperMock.mapToTitleDto(savedTitle)).thenReturn(titleDto);

        //When
        TitleDto expectedTitleDto = titleDbService.createTitle(titleDto);
        //Then
        assertEquals(1, expectedTitleDto.getTitleId());
        assertEquals("title", expectedTitleDto.getTitle());
        assertEquals("author", expectedTitleDto.getAuthor());
    }

    @Test
    void deleteTitleTest() {
        //Given
        //When
        titleDbService.deleteTitle(1L);
        //Then
        verify(titleRepositoryMock, times(1)).deleteById(1L);
    }
}