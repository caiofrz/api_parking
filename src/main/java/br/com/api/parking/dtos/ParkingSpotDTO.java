package br.com.api.parking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ParkingSpotDTO {

  @NotBlank
  private String spotNumber;
  @NotBlank
  private String responsibleName;
  @NotBlank
  @Size(max = 7, min = 7)
  private String licensePlateCar;
  @NotBlank
  private String brandCar;
  @NotBlank
  private String modelCar;
  @NotBlank
  private String colorCar;
  @NotBlank
  private String apartment;
  @NotBlank
  private String block;
}
