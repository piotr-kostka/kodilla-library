package com.crud.kodilla_library.controller.exceptions;

import com.crud.kodilla_library.domain.Title;

public class TitleNotFoundException extends EntityNotFoundException{

    public TitleNotFoundException(long id) {
        super(Title.class, id);
    }
}
