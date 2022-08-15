package com.crud.kodilla_library.mapper;

import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.dto.ExemplarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExemplarMapper {

    public Exemplar mapToExemplar(final ExemplarDto exemplarDto) {
        return new Exemplar(
                exemplarDto.getExemplarId(),
                exemplarDto.getTitle(),
                exemplarDto.getStatus(),
                exemplarDto.getRentals()
        );
    }

    public ExemplarDto mapToExemplarDto(final Exemplar exemplar) {
        return new ExemplarDto(
                exemplar.getExemplarId(),
                exemplar.getTitle(),
                exemplar.getStatus(),
                exemplar.getRentals()
        );
    }

    public List<ExemplarDto> mapToExemplarDtoList(final List<Exemplar> exemplarList) {
        return exemplarList.stream()
                .map(this::mapToExemplarDto)
                .collect(Collectors.toList());
    }
}
