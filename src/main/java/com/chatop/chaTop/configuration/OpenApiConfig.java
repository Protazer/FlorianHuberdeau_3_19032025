package com.chatop.chaTop.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a configuration class that configure the openApi/ swagger definition and security scheme.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Chatop API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Florian HUBERDEAU", email = "florian.huberdeau@laposte.net"
                ),
                license = @License(
                        name = "MIT License"
                ),
                description = "Chatop Rentals application API"
        ),
        servers = {
                @Server(description = "Local ENV", url = "http://localhost:3001/"),
                @Server(description = "Production ENV", url = "http://chatop.server/"),
        }
)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
