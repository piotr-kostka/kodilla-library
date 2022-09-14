package com.crud.kodilla_library.controller.exceptions;

import com.crud.kodilla_library.domain.User;

public class UserNotFoundException extends EntityNotFoundException{

    public UserNotFoundException(long id) {
        super(User.class, id);
    }
}
