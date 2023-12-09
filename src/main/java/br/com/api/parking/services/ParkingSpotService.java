package br.com.api.parking.services;

import br.com.api.parking.exceptions.NotFoundException;
import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.models.ParkingSpot;
import br.com.api.parking.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

  private final ParkingSpotRepository repository;

  public ParkingSpotService(ParkingSpotRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public ParkingSpot save(ParkingSpot parkingSpot) throws NotFoundException {
    if (this.repository.existsBySpotNumber(parkingSpot.getSpotNumber())) {
      throw new SpotAlreadyExistsException("Esta vaga já existe!");
    }
    return this.repository.save(parkingSpot);
  }

}
