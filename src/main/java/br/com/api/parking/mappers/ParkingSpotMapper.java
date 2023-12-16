package br.com.api.parking.mappers;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.models.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParkingSpotMapper {

  ParkingSpotMapper INSTANCE = Mappers.getMapper(ParkingSpotMapper.class);

  @Mapping(source = "spotNumber", target = "spotNumber")
  @Mapping(source = "responsibleName", target = "responsibleName")
  @Mapping(source = "licensePlateVehicle", target = "licensePlateVehicle")
  @Mapping(source = "brandVehicle", target = "brandVehicle")
  @Mapping(source = "modelVehicle", target = "modelVehicle")
  @Mapping(source = "colorVehicle", target = "colorVehicle")
  @Mapping(source = "apartment", target = "apartment")
  @Mapping(source = "block", target = "block")
  ParkingSpotDTO entityToDto(ParkingSpot parkingSpot);

  @Mapping(source = "spotNumber", target = "spotNumber")
  @Mapping(source = "responsibleName", target = "responsibleName")
  @Mapping(source = "licensePlateVehicle", target = "licensePlateVehicle")
  @Mapping(source = "brandVehicle", target = "brandVehicle")
  @Mapping(source = "modelVehicle", target = "modelVehicle")
  @Mapping(source = "colorVehicle", target = "colorVehicle")
  @Mapping(source = "apartment", target = "apartment")
  @Mapping(source = "block", target = "block")
  ParkingSpot dtoToEntity(ParkingSpotDTO parkingSpotDTO);
}
