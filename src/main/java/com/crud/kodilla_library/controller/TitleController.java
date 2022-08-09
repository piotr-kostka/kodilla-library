package com.crud.kodilla_library.controller;

import com.crud.kodilla_library.controller.exceptions.TitleNotFoundException;
import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.TitleDto;
import com.crud.kodilla_library.mapper.TitleMapper;
import com.crud.kodilla_library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/library/titles")
@RequiredArgsConstructor
public class TitleController {

    private final DbService service;
    private final TitleMapper titleMapper;

    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitles() {
        List<Title> titles = service.getAllTitles();
        return ResponseEntity.ok(titleMapper.mapToTitleDtoList(titles));
    }

    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable long titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(titleMapper.mapToTitleDto(service.getTitle(titleId)));
    }

    @DeleteMapping(value = "{titleId}")
    public ResponseEntity<Void> deleteTitle(@PathVariable long titleId) {
        service.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> updateTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        Title savedTitle = service.saveTitle(title);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(savedTitle));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto){
        Title title = titleMapper.mapToTitle(titleDto);
        service.saveTitle(title);
        return ResponseEntity.ok().build();
    }
}
