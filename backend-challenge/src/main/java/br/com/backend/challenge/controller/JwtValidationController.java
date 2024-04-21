package br.com.backend.challenge.controller;

import br.com.backend.challenge.model.JwtPayloadToken;
import br.com.backend.challenge.service.JwtValidationService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/v1")
public class JwtValidationController {

    @Inject
    JwtValidationService validationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Realiza a checagem do token JWT no padrão esperado.")
    @Path("/jwt-validate")
    public String getJwtDecoded(@Valid JwtPayloadToken jwtPayloadToken) {
        return validationService.validateJwtPayload(jwtPayloadToken);
    }
}