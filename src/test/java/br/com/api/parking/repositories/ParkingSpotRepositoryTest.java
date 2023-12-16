package br.com.api.parking.repositories;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.mappers.ParkingSpotMapper;
import br.com.api.parking.models.ParkingSpot;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ParkingSpotRepositoryTest {

  @Autowired
  private EntityManager entityManager;
  @Autowired
  private ParkingSpotRepository repository;

  @Test
  @DisplayName("Verifica o caso verdadeiro da existencia de uma vaga pelo seu numero")
  void shouldReturnTrueExistsBySpotNumber() {

    ParkingSpot parkingSpot = this.setup();

    var result = this.repository.existsBySpotNumber(parkingSpot.getSpotNumber());

    Assertions.assertThat(result).isEqualTo(Boolean.TRUE);

  }

  @Test
  @DisplayName("Verifica o caso falso da existencia de uma vaga pelo seu numero")
  void shouldReturnFalseExistsBySpotNumber() {

    var result = this.repository.existsBySpotNumber("123");

    Assertions.assertThat(result).isEqualTo(Boolean.FALSE);

  }

  @Test
  @DisplayName("Verifica o caso verdadeiro da existencia de uma vaga pelo apartamento e o bloco")
  void shouldReturnTrueExistsByApartmentAndBlock() {

    ParkingSpot parkingSpot = this.setup();

    var result =
            this.repository.existsByApartmentAndBlock(parkingSpot.getApartment(), parkingSpot.getBlock());

    Assertions.assertThat(result).isEqualTo(Boolean.TRUE);
  }

  @Test
  @DisplayName("Verifica o caso falso da existencia de uma vaga pelo apartamento e o bloco")
  void shouldReturnFalseExistsByApartmentAndBlock() {


    var result =
            this.repository.existsByApartmentAndBlock("123", "A");

    Assertions.assertThat(result).isEqualTo(Boolean.FALSE);
  }

  @Test
  void existsByLicensePlateVehicle() {
    ParkingSpot parkingSpot = this.setup();

    var result =
            this.repository.existsByLicensePlateVehicle(parkingSpot.getLicensePlateVehicle());

    Assertions.assertThat(result).isEqualTo(Boolean.TRUE);

  }

  private ParkingSpot createParkingSpot(ParkingSpotDTO parkingSpotDTO) {
    ParkingSpot parkingSpot = ParkingSpotMapper.INSTANCE.dtoToEntity(parkingSpotDTO);
    this.entityManager.persist(parkingSpot);
    return parkingSpot;
  }

  private ParkingSpot setup() {
    ParkingSpotDTO parkingSpotDto = new ParkingSpotDTO(
            "5B",
            "Alice Alves",
            "O13TZ20",
            "Chevrolet",
            "Celta",
            "Prata",
            "5B",
            "B");
    return this.createParkingSpot(parkingSpotDto);
  }
}