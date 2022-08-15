package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.TitleDto;
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

    @Test
    void mapToTitleTest() {
        //Given
        TitleDto titleDto = new TitleDto(1L, "title", "author", 2022, new HashSet<>());
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
        //Given
        Title title = new Title(1L, "title", "author", 2022, new HashSet<>());
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
        //Given
        Title title = new Title(1L, "title", "author", 2022, new HashSet<>());
        List<Title> list = new ArrayList<>();
        list.add(title);
        //When
        List<TitleDto> expected = titleMapper.mapToTitleDtoList(list);
        //Then
        assertEquals(1, expected.size());
    }
}