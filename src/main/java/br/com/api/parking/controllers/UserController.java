package br.com.api.parking.controllers;

import br.com.api.parking.dtos.UserDTO;
import br.com.api.parking.models.User;
import br.com.api.parking.services.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Users", description = "Endpoints para o controle dos usuários")
@RestController
@RequestMapping("users")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @ApiResponses({
          @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
          @ApiResponse(responseCode = "404", description = "Usuários não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @Parameters({
          @Parameter(name = "page", description = "Numero de paginas, comecando de 0"),
          @Parameter(name = "size", description = "Numero de itens por pagina "),
          @Parameter(name = "sort,direction", description = "Atributo de ordernação, direção da ordem")
  })
  @GetMapping
  public ResponseEntity<Page<User>> getAll(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll(pageable));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
          @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @GetMapping("/{id}")
  public ResponseEntity<User> getOne(@PathVariable UUID id) throws UsernameNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
          @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {@Content()}),
          @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @PutMapping("/{id}")
  public ResponseEntity<User> update(@Valid @RequestBody UserDTO parkingSpotDTO,
                                     @PathVariable UUID id) throws UsernameNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(parkingSpotDTO, id));
  }
  @ApiResponses({
          @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
          @ApiResponse(responseCode = "404", description = "Usuário não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) throws UsernameNotFoundException {
    this.service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
