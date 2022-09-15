package com.crud.kodilla_library.controller.exceptions;

import com.crud.kodilla_library.domain.Rental;

public class RentalNotFoundException extends EntityNotFoundException{

    public RentalNotFoundException(long id) {
        super(Rental.class, id);
    }

}
