package com.crud.kodilla_library.controller.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class className, long id) {
        super(className.getSimpleName() + " with id: " + id + " doesn't exist!");
    }
}
