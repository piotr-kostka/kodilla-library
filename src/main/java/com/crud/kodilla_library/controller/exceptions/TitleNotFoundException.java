package com.crud.kodilla_library.controller.exceptions;

public class TitleNotFoundException extends Exception{

    public TitleNotFoundException(final String message) {
        super(message);
    }
}
