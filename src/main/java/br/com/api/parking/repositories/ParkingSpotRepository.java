package br.com.api.parking.repositories;

import br.com.api.parking.models.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID> {

   boolean existsBySpotNumber(String spotNumber);
   boolean existsByApartmentAndBlock(String apartment, String block);
   boolean existsByLicensePlateCar(String licensePlateCar);
}
