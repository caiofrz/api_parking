package br.com.api.parking.services;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.exceptions.SpotNotFoundException;
import br.com.api.parking.mappers.ParkingSpotMapper;
import br.com.api.parking.models.ParkingSpot;
import br.com.api.parking.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParkingSpotService {

  private final ParkingSpotRepository repository;
  private final ParkingSpotMapper mapper = ParkingSpotMapper.INSTANCE;


  public ParkingSpotService(ParkingSpotRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public ParkingSpot save(ParkingSpotDTO parkingSpotDTO) {
    //TODO utilizar a lib MapStruct
    ParkingSpot parkingSpot = mapper.dtoToEntity(parkingSpotDTO);
    this.verifyExists(parkingSpot);
    return this.repository.save(parkingSpot);
  }

  public Page<ParkingSpot> findAll(Pageable pageable) {
    return this.repository.findAll(pageable);
  }

  public ParkingSpot findById(UUID id) {
    return this.find(id);
  }

  @Transactional
  public ParkingSpot update(ParkingSpotDTO parkingSpotDTO, UUID id) {
    ParkingSpot parkingSpot = mapper.dtoToEntity(parkingSpotDTO);
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
    String licensePlateVehicle = parkingSpot.getLicensePlateVehicle();
    String apartment = parkingSpot.getApartment();
    String block = parkingSpot.getBlock();

    if (this.repository.existsBySpotNumber(spotNumber)) {
      throw new SpotAlreadyExistsException("A vaga " + spotNumber + " já existe!");
    }
    if (this.repository.existsByLicensePlateVehicle(licensePlateVehicle)) {
      throw new SpotAlreadyExistsException("Já existe uma vaga cadastrada associada a placa " + licensePlateVehicle + "!");
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
