package br.com.api.parking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_parking_spot")
@Data
public class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true, length = 5)
  private String spotNumber;
  @Column(nullable = false, length = 80)
  private String responsibleName;
  @Column(nullable = false, unique = true, length = 7)
  private String licensePlateCar;
  @Column(nullable = false, length = 40)
  private String brandCar;
  @Column(nullable = false, length = 40)
  private String modelCar;
  @Column(nullable = false, length = 30)
  private String colorCar;
  @Column(nullable = false, length = 30)
  private String apartment;
  @Column(nullable = false, length = 30)
  private String block;

  @CreationTimestamp
  private String registrationDate;
}
