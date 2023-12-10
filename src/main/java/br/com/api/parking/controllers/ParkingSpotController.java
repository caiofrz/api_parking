package br.com.api.parking.controllers;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.exceptions.SpotNotFoundException;
import br.com.api.parking.models.ParkingSpot;
import br.com.api.parking.services.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/parking")
public class ParkingSpotController {

  private final ParkingSpotService service;

  public ParkingSpotController(ParkingSpotService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<ParkingSpot> save(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO)
          throws SpotAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(parkingSpotDTO));
  }

  @GetMapping
  public ResponseEntity<Page<ParkingSpot>> getAll(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ParkingSpot> getOne(@PathVariable UUID id) throws SpotNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ParkingSpot> update(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO,
                                            @PathVariable UUID id) throws SpotNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(parkingSpotDTO, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) throws SpotNotFoundException {
    this.service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
