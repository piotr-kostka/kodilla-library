package com.crud.kodilla_library.repository;

import com.crud.kodilla_library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends CrudRepository<Title, Long> {

    @Override
    List<Title> findAll();

    @Override
    Optional<Title> findById(Long titleId);

    @Override
    Title save(Title title);

    @Override
    void deleteById(Long titleId);
}
