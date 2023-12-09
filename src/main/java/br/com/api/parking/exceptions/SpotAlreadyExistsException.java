package br.com.api.parking.exceptions;

public class SpotAlreadyExistsException extends RuntimeException{
  public SpotAlreadyExistsException(String message) {
    super(message);
  }
}
