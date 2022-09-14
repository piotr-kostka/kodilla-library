package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.TitleDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TitleMapperTest {

    @InjectMocks
    private TitleMapper titleMapper;

    private Title title;
    private TitleDto titleDto;
    private List<Title> titleList = new ArrayList<>();

    @BeforeEach
    void init() {
        title = new Title(1L, "title", "author", 2022, new HashSet<>());
        titleDto = new TitleDto(1L, "title", "author", 2022, new HashSet<>());
        titleList.add(title);
    }

    @Test
    void mapToTitleTest() {
        //When
        Title expected = titleMapper.mapToTitle(titleDto);
        //Then
        assertEquals(1L, expected.getTitleId());
        assertEquals("title", expected.getTitle());
        assertEquals("author", expected.getAuthor());
        assertEquals(2022, expected.getPublicationYear());
    }

    @Test
    void mapToTitleDtoTest() {
        //When
        TitleDto expected = titleMapper.mapToTitleDto(title);
        //Then
        assertEquals(1L, expected.getTitleId());
        assertEquals("title", expected.getTitle());
        assertEquals("author", expected.getAuthor());
        assertEquals(2022, expected.getPublicationYear());
    }

    @Test
    void mapToTitleDtoListTest() {
        //When
        List<TitleDto> expected = titleMapper.mapToTitleDtoList(titleList);
        //Then
        assertEquals(1, expected.size());
    }
}