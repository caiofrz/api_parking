package br.com.api.parking.services;

import br.com.api.parking.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {

  private final ParkingSpotRepository repository;
  public ParkingSpotService(ParkingSpotRepository repository) {
    this.repository = repository;
  }



}
