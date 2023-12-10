package br.com.api.parking.exceptions;

public class SpotNotFoundException extends RuntimeException{
  public SpotNotFoundException(String message) {
    super(message);
  }
}
