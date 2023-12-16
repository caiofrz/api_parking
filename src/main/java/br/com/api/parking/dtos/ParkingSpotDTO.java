package br.com.api.parking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParkingSpotDTO {

  @NotBlank(message = "O numero da vaga não pode estar em branco!")
  private String spotNumber;
  @NotBlank(message = "O nome do responsável não pode estar em branco!")
  private String responsibleName;
  @NotBlank(message = "A placa do veiculo não pode estar em branco!")
  @Size(max = 7, min = 7, message = "A placa do veiculo deve conter 7 caracteres!")
  private String licensePlateVehicle;
  @NotBlank(message = "A marca do veiculo não pode estar em branco!")
  private String brandVehicle;
  @NotBlank(message = "O modelo do veiculo não pode estar em branco!")
  private String modelVehicle;
  @NotBlank(message = "A cor do veiculo não pode estar em branco!")
  private String colorVehicle;
  @NotBlank(message = "O numero do apartamento não pode estar em branco!")
  private String apartment;
  @NotBlank(message = "O numero do bloco não pode estar em branco!")
  private String block;
}
