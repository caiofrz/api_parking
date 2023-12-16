package br.com.api.parking.controllers;

import br.com.api.parking.dtos.ParkingSpotDTO;
import br.com.api.parking.exceptions.SpotAlreadyExistsException;
import br.com.api.parking.exceptions.SpotNotFoundException;
import br.com.api.parking.models.ParkingSpot;
import br.com.api.parking.models.User;
import br.com.api.parking.services.ParkingSpotService;
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
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "ParkingSpot", description = "Endpoints para o controle das vagas")
@RestController
@RequestMapping("spots")
public class ParkingSpotController {

  private final ParkingSpotService service;

  public ParkingSpotController(ParkingSpotService service) {
    this.service = service;
  }

  @ApiResponses({
          @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = ParkingSpot.class), mediaType = "application/json")}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
          @ApiResponse(responseCode = "409", description = "Já existe uma vaga cadastrada com esses dados!", content = {@Content()})
  })
  @PostMapping
  public ResponseEntity<ParkingSpot> save(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO)
          throws SpotAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(parkingSpotDTO));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(), mediaType = "application/json")}),
          @ApiResponse(responseCode = "404", description = "Spot não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @Parameters({
          @Parameter(name = "page", description = "Numero de paginas, comecando de 0"),
          @Parameter(name = "size", description = "Numero de itens por pagina "),
          @Parameter(name = "sort,direction", description = "Atributo de ordernação, direção da ordem")
  })
  @GetMapping
  public ResponseEntity<Page<ParkingSpot>> getAll(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll(pageable));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ParkingSpot.class), mediaType = "application/json")}),
          @ApiResponse(responseCode = "404", description = "Spot não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", content = {@Content()}, description = "Falha na verificação do Token!"),
  })
  @GetMapping("/{id}")
  public ResponseEntity<ParkingSpot> getOne(@PathVariable UUID id) throws SpotNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
          @ApiResponse(responseCode = "400", description = "Erro na requisição", content = {@Content()}),
          @ApiResponse(responseCode = "404", description = "Spot não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @PutMapping("/{id}")
  public ResponseEntity<ParkingSpot> update(@Valid @RequestBody ParkingSpotDTO parkingSpotDTO,
                                            @PathVariable UUID id) throws SpotNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.service.update(parkingSpotDTO, id));
  }

  @ApiResponses({
          @ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(), mediaType = "application/json")}),
          @ApiResponse(responseCode = "404", description = "Spot não encontrado!", content = {@Content()}),
          @ApiResponse(responseCode = "403", content = {@Content()}),
          @ApiResponse(responseCode = "401", description = "Falha na verificação do Token!", content = {@Content()}),
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) throws SpotNotFoundException {
    this.service.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
