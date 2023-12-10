package br.com.api.parking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ParkingSpotDTO {

  @NotBlank(message = "O numero da vaga não pode estar em branco!")
  private String spotNumber;
  @NotBlank(message = "O nome do responsável não pode estar em branco!")
  private String responsibleName;
  @NotBlank(message = "A placa do carro não pode estar em branco!")
  @Size(max = 7, min = 7, message = "A placa do carro deve conter 7 caracteres!")
  private String licensePlateCar;
  @NotBlank(message = "A marca do carro não pode estar em branco!")
  private String brandCar;
  @NotBlank(message = "O modelo do carro não pode estar em branco!")
  private String modelCar;
  @NotBlank(message = "A cor do carro não pode estar em branco!")
  private String colorCar;
  @NotBlank(message = "O numero do apartamento não pode estar em branco!")
  private String apartment;
  @NotBlank(message = "O numero do bloco não pode estar em branco!")
  private String block;
}
