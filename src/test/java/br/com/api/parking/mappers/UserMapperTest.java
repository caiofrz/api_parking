package br.com.api.parking.mappers;

import br.com.api.parking.dtos.UserDTO;
import br.com.api.parking.enums.UserRole;
import br.com.api.parking.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserMapperTest {


  @Test
  void shouldMapDTOToEntity() {
    //given
    UserDTO userDTO = new UserDTO(
            "Caio Ferraz",
            "caio@teste.com",
            "12345678",
            "ADMIN");

    User user = UserMapper.INSTANCE.dtoToEntity(userDTO);


    Assertions.assertThat(user).isNotNull();
    Assertions.assertThat(user.getName()).isEqualTo("Caio Ferraz");
    Assertions.assertThat(user.getEmail()).isEqualTo("caio@teste.com");
    Assertions.assertThat(user.getPassword()).isEqualTo("12345678");
    Assertions.assertThat(user.getRole()).isEqualTo(UserRole.ADMIN);

  }
}