package com.crud.kodilla_library.service;

import com.crud.kodilla_library.controller.exceptions.TitleNotFoundException;
import com.crud.kodilla_library.domain.Title;
import com.crud.kodilla_library.domain.dto.TitleDto;
import com.crud.kodilla_library.mapper.TitleMapper;
import com.crud.kodilla_library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper;

    public List<TitleDto> getAllTitles() {
        List<Title> titles = titleRepository.findAll();
        return titleMapper.mapToTitleDtoList(titles);
    }

    public TitleDto getTitle(final long titleId) throws TitleNotFoundException {
        Title title = titleRepository.findById(titleId).orElseThrow(TitleNotFoundException::new);
        return titleMapper.mapToTitleDto(title);
    }

    @Transactional
    public TitleDto createTitle(final TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        Title savedTitle = titleRepository.save(title);
        return titleMapper.mapToTitleDto(savedTitle);
    }

    @Transactional
    public void deleteTitle(final Long titleId) {
        titleRepository.deleteById(titleId);
    }
}
