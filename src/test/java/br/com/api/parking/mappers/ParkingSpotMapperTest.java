package br.com.api.parking.mappers;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.models.ParkingSpot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParkingSpotMapperTest {

  @Test
  void shouldMapDTOToEntity() {
    //given
    ParkingSpotDTO parkingSpotDto = new ParkingSpotDTO(
            "5B",
            "Alice Alves",
            "O13TZ20",
            "Chevrolet",
            "Celta",
            "Prata",
            "5B",
            "B");

    ParkingSpot parkingSpot = ParkingSpotMapper.INSTANCE.dtoToEntity(parkingSpotDto);

    Assertions.assertThat(parkingSpot).isNotNull();
    Assertions.assertThat(parkingSpot.getSpotNumber()).isEqualTo("5B");
    Assertions.assertThat(parkingSpot.getResponsibleName()).isEqualTo("Alice Alves");
    Assertions.assertThat(parkingSpot.getLicensePlateVehicle()).isEqualTo("O13TZ20");
    Assertions.assertThat(parkingSpot.getBrandVehicle()).isEqualTo("Chevrolet");
    Assertions.assertThat(parkingSpot.getModelVehicle()).isEqualTo("Celta");
    Assertions.assertThat(parkingSpot.getColorVehicle()).isEqualTo("Prata");
    Assertions.assertThat(parkingSpot.getApartment()).isEqualTo("5B");
    Assertions.assertThat(parkingSpot.getBlock()).isEqualTo("B");
  }
}
