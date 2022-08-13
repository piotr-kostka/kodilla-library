package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.ExemplarNotFoundException;
import com.crud.kodilla_library.domain.dto.ExemplarDto;
import com.crud.kodilla_library.service.ExemplarDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/exemplars")
@RequiredArgsConstructor
public class ExemplarController {

    private final ExemplarDbService exemplarDbService;

    @GetMapping
    public List<ExemplarDto> getExemplars() {
        return exemplarDbService.getAllExemplars();
    }

    @GetMapping(value = "{exemplarId}")
    public ExemplarDto getExemplar(@PathVariable long exemplarId) throws ExemplarNotFoundException {
        return exemplarDbService.getExemplar(exemplarId);
    }

    @GetMapping(value = "/available")
    public List<ExemplarDto> getAvailableExemplars() {
        return exemplarDbService.getAvailableExemplars();
    }

    @GetMapping(value = "/rented")
    public List<ExemplarDto> getRentedExemplars() {
        return exemplarDbService.getRentedExemplars();
    }

    @GetMapping(value = "/lost")
    public List<ExemplarDto> getLostExemplars() {
        return exemplarDbService.getLostExemplars();
    }

    @GetMapping(value = "/destroyed")
    public List<ExemplarDto> getDestroyedExemplars() {
        return exemplarDbService.getDestroyedExemplars();
    }

    @DeleteMapping(value = "{exemplarId}")
    public void deleteExemplar(@PathVariable long exemplarId) {
        exemplarDbService.deleteExemplar(exemplarId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExemplarDto updateExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarDbService.createExemplar(exemplarDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ExemplarDto createExemplar(@RequestBody ExemplarDto exemplarDto) {
        return exemplarDbService.createExemplar(exemplarDto);
    }
}
