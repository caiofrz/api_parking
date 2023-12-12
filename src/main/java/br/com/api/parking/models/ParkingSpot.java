package br.com.api.parking.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Schema(description = "ParkingSpot Model")
@Entity
@Table(name = "tb_parking_spot")
@Data
public class ParkingSpot {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ParkingSpot Id", type = "UUID")
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(description = "Numero da vaga", example = "1A")
  @Column(nullable = false, unique = true, length = 5)
  private String spotNumber;
  @Schema(description = "Nome do responsável pela da vaga", example = "Caio Ferraz")
  @Column(nullable = false, length = 80)
  private String responsibleName;
  @Schema(description = "Placa do veículo", example = "O13PZ20")
  @Column(nullable = false, unique = true, length = 7)
  private String licensePlateCar;
  @Schema(description = "Marca do veículo", example = "Fiat")
  @Column(nullable = false, length = 40)
  private String brandCar;
  @Schema(description = "Modelo do veículo", example = "Palio")
  @Column(nullable = false, length = 40)
  private String modelCar;
  @Schema(description = "Cor do veículo", example = "Preto")
  @Column(nullable = false, length = 30)
  private String colorCar;
  @Schema(description = "Numero do apartamento", example = "1A")
  @Column(nullable = false, length = 30)
  private String apartment;
  @Schema(description = "Numero do bloco", example = "A")
  @Column(nullable = false, length = 30)
  private String block;

  @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Data de registro da vaga")
  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp registrationDate;
}
