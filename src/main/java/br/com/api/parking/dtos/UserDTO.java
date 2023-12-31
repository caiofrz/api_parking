package br.com.api.parking.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {

  @NotBlank
  private String name;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres")
  private String password;
  @NotBlank
  private String role;
}
