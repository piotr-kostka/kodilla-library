package com.crud.kodilla_library.controller.exceptions;

import com.crud.kodilla_library.domain.Exemplar;

public class ExemplarNotFoundException extends EntityNotFoundException{

    public ExemplarNotFoundException(long id) {
        super(Exemplar.class, id);
    }
}
