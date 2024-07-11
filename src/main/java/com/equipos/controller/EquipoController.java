package com.equipos.controller;

import com.equipos.exception.ErrorDetails;
import com.equipos.model.dto.EquipoDTO;
import com.equipos.model.payload.EquipoPayload;
import com.equipos.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @Operation(summary = "obtención de todos los equipos",
            security = @SecurityRequirement(name = "JWT"),
            tags = {"Equipos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class, types = "array"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectUtils.Null.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))})
    @GetMapping
    public ResponseEntity<List<EquipoDTO>> getAll() {
        return ResponseEntity.ok(equipoService.getAll());
    }

    @Operation(summary = "obtención de un equipo por el id proporcionado",
            security = @SecurityRequirement(name = "JWT"),
            tags = {"Equipos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectUtils.Null.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))})
    @GetMapping(value = "/{id}")
    public ResponseEntity<EquipoDTO> getById(@Parameter(in = ParameterIn.PATH, description = "id equipo", required = true, schema = @Schema()) @PathVariable("id") Long id) {
        return ResponseEntity.ok(equipoService.getById(id));
    }

    @Operation(summary = "obtención de equipos por nombre",
            security = @SecurityRequirement(name = "JWT"),
            tags = {"Equipos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class, types = "array"))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectUtils.Null.class))),
            })
    @GetMapping(value = "/buscar")
    public ResponseEntity<List<EquipoDTO>> getContainingNombre(@Parameter(in = ParameterIn.QUERY, description = "nombre equipo", schema = @Schema()) @RequestParam("nombre") String nombre) {
        return ResponseEntity.ok(equipoService.findByNombre(nombre));
    }

    @Operation(summary = "persistencia de un equipo",
            security = @SecurityRequirement(name = "JWT"),
            tags = {"Equipos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectUtils.Null.class))),})
    @PostMapping
    public ResponseEntity<EquipoDTO> persistEquipo(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @Valid @RequestBody EquipoPayload equipoPayload) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipoService.persistEquipo(equipoPayload));
    }

    @Operation(summary = "actualización de un equipo",
            security = @SecurityRequirement(name = "JWT"),
            tags = {"Equipos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectUtils.Null.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))})
    @PutMapping(value = "/{id}")
    public ResponseEntity<EquipoDTO> updateEquipo(@Parameter(in = ParameterIn.PATH, description = "id equipo", required = true, schema = @Schema()) @PathVariable("id") Long id,
                                                  @Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema()) @Valid @RequestBody EquipoPayload equipoPayload) {
        return ResponseEntity.ok(equipoService.updateEquipo(id, equipoPayload));
    }

    @Operation(summary = "eliminar un equipo",
            security = @SecurityRequirement(name = "JWT"),
            tags = {"Equipos"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ObjectUtils.Null.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteEquipo(@Parameter(in = ParameterIn.PATH, description = "id equipo", required = true, schema = @Schema()) @PathVariable("id") Long id) {
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }
}
