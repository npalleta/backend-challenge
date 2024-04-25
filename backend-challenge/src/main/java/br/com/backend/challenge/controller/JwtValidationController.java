package br.com.backend.challenge.controller;

import br.com.backend.challenge.model.JwtPayloadToken;
import br.com.backend.challenge.service.JwtValidationService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Slf4j
@Path("/api/v1")
public class JwtValidationController {

    private final JwtValidationService jwtValidationService;

    @Inject
    public JwtValidationController(JwtValidationService jwtValidationService) {
        this.jwtValidationService = jwtValidationService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Realiza a checagem do token JWT no padrão esperado.")
    @Path("/jwt-validate")
    public Response getJwtDecoded(@Valid JwtPayloadToken jwtPayloadToken) {
        try {
            log.info("-- Validando um token JWT proprietário --");
            if (jwtPayloadToken != null) {
                String decodedToken = this.jwtValidationService.validateJwtPayload(jwtPayloadToken);
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

    @POST
    @Operation(summary = "Realiza a checagem do token JWT - passagem por paramêtro.")
    @Path("/jwt-validate/{jwtPayloadToken}")
    public Response getJwtDecoded(@PathParam("jwtPayloadToken") @Valid String jwtPayloadToken) {
        try {
            log.info("-- Validando um token JWT proprietário --");
            if (jwtPayloadToken != null && !jwtPayloadToken.isEmpty()) {
                String decodedToken = this.jwtValidationService.validateJwtPayload(jwtPayloadToken);
                String result = "{\"result\": \"" + decodedToken + "\"}";

                return Response.status(Response.Status.CREATED)
                    .entity(result)
                    .build();
                //
            } else {
                log.error("O JWT Web Token não foi fornecido na requisição.");
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("O JWT Web Token não foi fornecido na requisição.")
                    .build();
                //
            }
        } catch (RuntimeException ex) {
            log.error("Ocorreu um erro ao validar o token JWT.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Ocorreu um erro ao validar o token JWT.")
                .build();
            //
        }
    }
}