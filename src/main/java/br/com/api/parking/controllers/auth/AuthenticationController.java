package br.com.api.parking.controllers.auth;

import br.com.api.parking.config.security.TokenService;
import br.com.api.parking.dtos.LoginRequestDTO;
import br.com.api.parking.dtos.LoginResponseDTO;
import br.com.api.parking.dtos.UserDTO;
import br.com.api.parking.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final UserService service;
  private final TokenService tokenService;


  public AuthenticationController(AuthenticationManager authenticationManager, UserService service, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.service = service;
    this.tokenService = tokenService;
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(userDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
    Authentication authenticationRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    Authentication authenticationResponse =
            this.authenticationManager.authenticate(authenticationRequest);
    var token = this.tokenService.generateToken((User) authenticationResponse.getPrincipal());
    return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(token));
  }
}
