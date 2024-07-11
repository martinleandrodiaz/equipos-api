package com.equipos.controller;

import com.equipos.exception.ErrorDetails;
import com.equipos.model.dto.AuthenticationDTO;
import com.equipos.model.payload.AuthenticationPayload;
import com.equipos.service.AuthenticationManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {

    private final AuthenticationManagerService authenticationManagerService;

    public AuthenticationController(AuthenticationManagerService authenticationManagerService) {
        this.authenticationManagerService = authenticationManagerService;
    }

    @Operation(summary = "autenticación",
            tags = {"Auth"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))})
    @PostMapping("/login")
    public ResponseEntity<AuthenticationDTO> login(@Parameter(in = ParameterIn.DEFAULT, schema = @Schema()) @Valid @RequestBody AuthenticationPayload authenticationPayload) {
        return ResponseEntity.ok(authenticationManagerService.authenticate(authenticationPayload));
    }
}
