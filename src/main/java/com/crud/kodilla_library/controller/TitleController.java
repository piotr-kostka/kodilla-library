package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.TitleNotFoundException;
import com.crud.kodilla_library.domain.dto.TitleDto;
import com.crud.kodilla_library.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleDbService titleDbService;

    @GetMapping
    public List<TitleDto> getTitles() {
        return titleDbService.getAllTitles();
    }

    @GetMapping(value = "{titleId}")
    public TitleDto getTitle(@PathVariable long titleId) throws TitleNotFoundException {
        return titleDbService.getTitle(titleId);
    }

    @DeleteMapping(value = "{titleId}")
    public void deleteTitle(@PathVariable long titleId) {
        titleDbService.deleteTitle(titleId);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto updateTitle(@RequestBody TitleDto titleDto) {
        return titleDbService.createTitle(titleDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TitleDto createTitle(@RequestBody TitleDto titleDto) {
        return titleDbService.createTitle(titleDto);
    }
}
