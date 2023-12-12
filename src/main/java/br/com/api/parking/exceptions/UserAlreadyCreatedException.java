package br.com.api.parking.exceptions;

public class UserAlreadyCreatedException extends RuntimeException {
  public UserAlreadyCreatedException(String message) {
    super(message);
  }
}
