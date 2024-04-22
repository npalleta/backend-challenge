package br.com.backend.challenge.controller;

import br.com.backend.challenge.model.JwtPayloadToken;
import br.com.backend.challenge.service.JwtValidationService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Slf4j
@Path("/api/v1")
public class JwtValidationController {

    @Inject
    JwtValidationService validationService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Realiza a checagem do token JWT no padrão esperado.")
    @Path("/jwt-validate")
    public Response getJwtDecoded(@Valid JwtPayloadToken jwtPayloadToken) {
        try {
            log.info("-- Validando um token JWT proprietário --");
            if (jwtPayloadToken != null) {
                String decodedToken = validationService.validateJwtPayload(jwtPayloadToken);
                return Response.status(Response.Status.CREATED).entity(decodedToken).build();
            }
        } catch (IndexOutOfBoundsException ex) {
            log.error("Verifique o corpo da requisição - token inválido ou inexistente.");
        } catch (RuntimeException ex) {
            log.error("O JWT Web Token foi definido de forma inválida na requisição.");
        }
        return Response
            .status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Verifique o corpo da requisição - token inválido ou inexistente.")
            .status(Response.Status.BAD_REQUEST)
            .entity("O JWT Web Token foi definido de forma inválida na requisição.")
            .build();
        //
    }
}