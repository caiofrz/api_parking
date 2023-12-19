package br.com.api.parking.controllers;

import br.com.api.parking.exceptions.ErrorResponse;
import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.exceptions.SpotNotFoundException;
import br.com.api.parking.exceptions.UserAlreadyCreatedException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SpotAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleSpotRegistrationException(SpotAlreadyExistsException ex,
                                                                       HttpServletRequest request) {
    return response(ex.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
  }

  @ExceptionHandler(SpotNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleSpotNotFoundException(SpotNotFoundException ex,
                                                                   HttpServletRequest request) {
    return response(ex.getMessage(), request, HttpStatus.NOT_FOUND, LocalDateTime.now());
  }

  @ExceptionHandler(UserAlreadyCreatedException.class)
  public ResponseEntity<ErrorResponse> handleUserRegistrationException(UserAlreadyCreatedException ex,
                                                                       HttpServletRequest request) {

    return response(ex.getMessage(), request, HttpStatus.CONFLICT, LocalDateTime.now());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleUserRegistrationException(MethodArgumentNotValidException ex,
                                                                       HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    return response(errors.toString(), request, HttpStatus.BAD_REQUEST, LocalDateTime.now());
  }

  @ExceptionHandler(JWTCreationException.class)
  public ResponseEntity<ErrorResponse> handleJWTCreationException(JWTCreationException ex,
                                                                  HttpServletRequest request) {
    return response(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
  }

  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<ErrorResponse> handleJWTVerificationException(JWTVerificationException ex,
                                                                      HttpServletRequest request) {
    return response(ex.getMessage(), request, HttpStatus.UNAUTHORIZED, LocalDateTime.now());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex,
                                                                       HttpServletRequest request) {
    return response(ex.getMessage(), request, HttpStatus.NOT_FOUND, LocalDateTime.now());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex,
                                                                     HttpServletRequest request) {
    return response(ex.getMessage(), request, HttpStatus.FORBIDDEN, LocalDateTime.now());
  }

  private ResponseEntity<ErrorResponse> response(final String message,
                                                 final HttpServletRequest request,
                                                 final HttpStatus status,
                                                 LocalDateTime timestamp) {
    return ResponseEntity
            .status(status)
            .body(new ErrorResponse(timestamp, status.value(), message, request.getRequestURI()));
  }
}

