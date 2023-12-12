package br.com.api.parking.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequestDTO {

  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Min(value = 8)
  private String password;
}
