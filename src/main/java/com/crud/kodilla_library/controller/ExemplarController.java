package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.ExemplarNotFoundException;
import com.crud.kodilla_library.domain.Exemplar;
import com.crud.kodilla_library.domain.dto.ExemplarDto;
import com.crud.kodilla_library.mapper.ExemplarMapper;
import com.crud.kodilla_library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/exemplars")
@RequiredArgsConstructor
public class ExemplarController {

    private final DbService service;
    private final ExemplarMapper exemplarMapper;

    @GetMapping
    public ResponseEntity<List<ExemplarDto>> getExemplars() {
        List<Exemplar> exemplars = service.getAllExemplars();
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDtoList(exemplars));
    }

    @GetMapping(value = "{exemplarId}")
    public ResponseEntity<ExemplarDto> getExemplar(@PathVariable long exemplarId) throws ExemplarNotFoundException {
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDto(service.getExemplar(exemplarId)));
    }

    @GetMapping(value = "/available")
    public ResponseEntity<List<ExemplarDto>> getAvailableExemplars() {
        List<Exemplar> available = service.getAvailableExemplars();
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDtoList(available));
    }

    @GetMapping(value = "/rented")
    public ResponseEntity<List<ExemplarDto>> getRentedExemplars() {
        List<Exemplar> rented = service.getRentedExemplars();
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDtoList(rented));
    }

    @GetMapping(value = "/lost")
    public ResponseEntity<List<ExemplarDto>> getLostExemplars() {
        List<Exemplar> lost = service.getLostExemplars();
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDtoList(lost));
    }

    @GetMapping(value = "/destroyed")
    public ResponseEntity<List<ExemplarDto>> getDestroyedExemplars() {
        List<Exemplar> destroyed = service.getDestroyedExemplars();
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDtoList(destroyed));
    }

    @DeleteMapping(value = "{exemplarId}")
    public ResponseEntity<Void> deleteExemplar(@PathVariable long exemplarId) {
        service.deleteExemplar(exemplarId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExemplarDto> updateExemplar(@RequestBody ExemplarDto exemplarDto) {
        Exemplar exemplar = exemplarMapper.mapToExemplar(exemplarDto);
        Exemplar savedExemplar = service.saveExemplar(exemplar);
        return ResponseEntity.ok(exemplarMapper.mapToExemplarDto(savedExemplar));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createExemplar(@RequestBody ExemplarDto exemplarDto) {
        Exemplar exemplar = exemplarMapper.mapToExemplar(exemplarDto);
        service.saveExemplar(exemplar);
        return ResponseEntity.ok().build();
    }
}
