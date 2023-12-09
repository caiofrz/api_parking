package br.com.api.parking.controllers;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.exceptions.NotFoundException;
import br.com.api.parking.models.ParkingSpot;
import br.com.api.parking.repositories.ParkingSpotRepository;
import br.com.api.parking.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/parking")
public class ParkingSpotController {

  private final ParkingSpotService service;
  public ParkingSpotController(ParkingSpotService service, ParkingSpotRepository repository) {
    this.service = service;
  }


  @PostMapping
  public ResponseEntity<ParkingSpot> save(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO) throws NotFoundException {
    ParkingSpot parkingSpot = new ParkingSpot();
    BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(parkingSpot));
  }
}
