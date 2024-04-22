package br.com.backend.challenge.controller;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "backend-challenge",
                version = "1.0.0-BETA",
                description = "API de validação de token JWT.",
                contact = @Contact(
                        name = "Niky Lima",
                        url = "https://github.com/npalleta/backend-challenge",
                        email = "devqasp@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https:www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class JwtApiDescription extends Application {
}