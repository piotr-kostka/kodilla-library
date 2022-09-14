package com.crud.kodilla_library.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(TitleNotFoundException.class)
//    public ResponseEntity<Object> handleTitleNotFoundException(TitleNotFoundException titleNotFoundException) {
//        return new ResponseEntity<>("Title with given ID doesn't exists!", HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ExemplarNotFoundException.class)
//    public ResponseEntity<Object> handleExemplarNotFoundException(ExemplarNotFoundException exemplarNotFoundException) {
//        return new ResponseEntity<>("Exemplar with given ID doesn't exists", HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(RentalNotFoundException.class)
//    public ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException rentalNotFoundException) {
//        return new ResponseEntity<>("Rental with given ID doesn't exists", HttpStatus.BAD_REQUEST);
//    }
}
