package com.crud.kodilla_library.repository;

import com.crud.kodilla_library.domain.Exemplar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExemplarRepository extends CrudRepository<Exemplar, Long> {

    @Override
    List<Exemplar> findAll();

    @Override
    Optional<Exemplar> findById(Long exemplarId);

    @Override
    Exemplar save(Exemplar exemplar);

    @Override
    void deleteById(Long exemplarId);
}
