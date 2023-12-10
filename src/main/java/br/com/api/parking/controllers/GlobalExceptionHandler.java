package br.com.api.parking.controllers;

import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.exceptions.SpotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SpotAlreadyExistsException.class)
  public ResponseEntity<String> handleSpotRegistrationException(SpotAlreadyExistsException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(SpotNotFoundException.class)
  public  ResponseEntity<String> handleSpotNotFoundException(SpotNotFoundException ex){
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }
}

