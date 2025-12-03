package com.example.Travelgo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
// Define la información básica de la API
@OpenAPIDefinition(
    info = @Info(
        title = "API Travelgo Backend",
        version = "0.0.1",
        description = "Documentación de la API para la aplicación Travelgo, incluyendo autenticación JWT."
    )
)
//  Define el esquema de seguridad JWT 
@SecurityScheme(
    name = "Bearer Authentication", // Nombre que se mostrará en Swagger UI
    type = SecuritySchemeType.HTTP,  // Tipo de esquema: HTTP
    bearerFormat = "JWT",            // Formato del token: JWT
    scheme = "bearer",               // Tipo de autenticación: bearer
    description = "Ingresa el token JWT obtenido en el endpoint de login. Ejemplo: 'eyJhbGciOiJIUzI1Ni...' "
)
public class SwaggerConfig {
    
}
