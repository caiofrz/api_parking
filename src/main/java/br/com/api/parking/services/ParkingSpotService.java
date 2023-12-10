package br.com.api.parking.services;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.exceptions.SpotNotFoundException;
import br.com.api.parking.models.ParkingSpot;
import br.com.api.parking.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotService {

  private final ParkingSpotRepository repository;

  public ParkingSpotService(ParkingSpotRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public ParkingSpot save(ParkingSpotDTO parkingSpotDTO) {
    //TODO utilizar a lib MapStruct
    ParkingSpot parkingSpot = new ParkingSpot();
    BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);
    this.verifyExists(parkingSpot);
    return this.repository.save(parkingSpot);
  }

  public List<ParkingSpot> findAll() {
    return this.repository.findAll();
  }

  public ParkingSpot findById(UUID id) {
    return this.find(id);
  }

  @Transactional
  public ParkingSpot update(ParkingSpotDTO parkingSpotDTO, UUID id) {
    ParkingSpot parkingSpot = new ParkingSpot();
    BeanUtils.copyProperties(parkingSpotDTO, parkingSpot);
    this.find(id);
    parkingSpot.setId(id);
    return this.repository.save(parkingSpot);
  }

  @Transactional
  public void delete(UUID id) {
    this.find(id);
    this.repository.deleteById(id);
  }

  private void verifyExists(ParkingSpot parkingSpot) {
    String spotNumber = parkingSpot.getSpotNumber();
    String licensePlateCar = parkingSpot.getLicensePlateCar();
    String apartment = parkingSpot.getApartment();
    String block = parkingSpot.getBlock();

    if (this.repository.existsBySpotNumber(spotNumber)) {
      throw new SpotAlreadyExistsException("A vaga " + spotNumber + " já existe!");
    }
    if (this.repository.existsByLicensePlateCar(licensePlateCar)) {
      throw new SpotAlreadyExistsException("Já existe uma vaga cadastrada associada a placa " + licensePlateCar + "!");
    }
    if (this.repository.existsByApartmentAndBlock(apartment, block)) {
      throw new SpotAlreadyExistsException(
              "Já existe uma vaga cadastrada associada ao apartamento " + apartment + "bloco " + block + "!"
      );
    }
  }

  private ParkingSpot find(UUID id) {
    return this.repository.findById(id)
            .orElseThrow(
                    () -> new SpotNotFoundException("Nenhuma vaga correspondente a este id foi identificada")
            );
  }

}
