package br.com.api.parking.controllers;

import br.com.api.parking.dtos.UserDTO;
import br.com.api.parking.models.User;
import br.com.api.parking.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Page<User>> getAll(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getOne(@PathVariable UUID id) throws UsernameNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(@Valid @RequestBody UserDTO parkingSpotDTO,
                                     @PathVariable UUID id) throws UsernameNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(parkingSpotDTO, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) throws UsernameNotFoundException {
    this.service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
