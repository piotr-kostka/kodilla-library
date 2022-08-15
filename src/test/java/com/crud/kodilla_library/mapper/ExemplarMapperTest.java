package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.ExemplarStatus;
import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.ExemplarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExemplarMapperTest {

    @InjectMocks
    private ExemplarMapper exemplarMapper;

    public Title initTitle() {
        return new Title(1L, "title", "author", 2022, new HashSet<>());
    }

    @Test
    void mapToExemplarTest() {
        //Given
        ExemplarDto exemplarDto = new ExemplarDto(1L, initTitle(), ExemplarStatus.AVAILABLE, new HashSet<>());
        //When
        Exemplar expected = exemplarMapper.mapToExemplar(exemplarDto);
        //Then
        assertEquals(1L, expected.getExemplarId());
        assertEquals("title", expected.getTitle().getTitle());
        assertEquals(2022, expected.getTitle().getPublicationYear());
        assertEquals(ExemplarStatus.AVAILABLE, expected.getStatus());
    }

    @Test
    void mapToExemplarDtoTest() {
        //Given
        Exemplar exemplar = new Exemplar(1L, initTitle(), ExemplarStatus.AVAILABLE, new HashSet<>());
        //When
        ExemplarDto expected = exemplarMapper.mapToExemplarDto(exemplar);
        //Then
        assertEquals(1L, expected.getExemplarId());
        assertEquals("title", expected.getTitle().getTitle());
        assertEquals(2022, expected.getTitle().getPublicationYear());
        assertEquals(ExemplarStatus.AVAILABLE, expected.getStatus());
    }

    @Test
    void mapToExemplarDtoListTest() {
        //Given
        Exemplar exemplar = new Exemplar(1L, initTitle(), ExemplarStatus.AVAILABLE, new HashSet<>());
        List<Exemplar> list = new ArrayList<>();
        list.add(exemplar);
        //When
        List<ExemplarDto> expected = exemplarMapper.mapToExemplarDtoList(list);
        //Then
        assertEquals(1, expected.size());
    }
}