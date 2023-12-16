package br.com.api.parking.mappers;

import br.com.api.parking.dtos.UserDTO;
import br.com.api.parking.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "password")
  @Mapping(target = "role", defaultExpression = "java(UserRole.valueOf(userDTO.getRole()))")
  User dtoToEntity(UserDTO userDTO);

  @Mapping(source = "name", target = "name")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "password")
  UserDTO entityToDto(User user);
}
