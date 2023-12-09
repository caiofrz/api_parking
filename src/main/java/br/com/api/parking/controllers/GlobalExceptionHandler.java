package br.com.api.parking.controllers;

import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SpotAlreadyExistsException.class)
  public ResponseEntity<String> handleUserRegistrationException(SpotAlreadyExistsException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}

