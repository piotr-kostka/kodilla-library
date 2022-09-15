package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.ExemplarNotFoundException;
import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.ExemplarStatus;
import com.crud.kodilla_library.domain.dto.ExemplarDto;
import com.crud.kodilla_library.mapper.ExemplarMapper;
import com.crud.kodilla_library.repository.ExemplarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExemplarDbService {

    private final ExemplarRepository exemplarRepository;
    private final ExemplarMapper exemplarMapper;

    public List<ExemplarDto> getAllExemplars() {
        List<Exemplar> exemplars = exemplarRepository.findAll();
        return exemplarMapper.mapToExemplarDtoList(exemplars);
    }

    public ExemplarDto getExemplar(final long exemplarId) throws ExemplarNotFoundException {
        Exemplar exemplar = exemplarRepository.findById(exemplarId).orElseThrow(() -> new ExemplarNotFoundException(exemplarId));
        return exemplarMapper.mapToExemplarDto(exemplar);
    }

    public List<ExemplarDto> getAvailableExemplars() {
        List<Exemplar> availableExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.AVAILABLE))
                .collect(Collectors.toList());
        return exemplarMapper.mapToExemplarDtoList(availableExemplars);
    }

    public List<ExemplarDto> getRentedExemplars() {
        List<Exemplar> rentedExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.RENTED))
                .collect(Collectors.toList());
        return exemplarMapper.mapToExemplarDtoList(rentedExemplars);
    }

    public List<ExemplarDto> getLostExemplars() {
        List<Exemplar> lostExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.LOST))
                .collect(Collectors.toList());
        return exemplarMapper.mapToExemplarDtoList(lostExemplars);
    }

    public List<ExemplarDto> getDestroyedExemplars() {
        List<Exemplar> destroyedExemplars = exemplarRepository.findAll().stream()
                .filter(x -> x.getStatus().equals(ExemplarStatus.DESTROYED))
                .collect(Collectors.toList());
        return exemplarMapper.mapToExemplarDtoList(destroyedExemplars);
    }

    @Transactional
    public ExemplarDto createExemplar(final ExemplarDto exemplarDto) {
        Exemplar exemplar = exemplarMapper.mapToExemplar(exemplarDto);
        Exemplar savedExemplar = exemplarRepository.save(exemplar);
        return exemplarMapper.mapToExemplarDto(savedExemplar);
    }

    @Transactional
    public void deleteExemplar(final long exemplarId) {
        exemplarRepository.deleteById(exemplarId);
    }

}
