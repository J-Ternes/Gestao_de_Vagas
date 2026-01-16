package com.jonathan.Gestao_Vagas.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.event.WindowFocusListener;

import static java.awt.SystemColor.info;

@Configuration
public class SwaggerConfig {

    @Bean //Sobrescrever uma implementação que ja existe
    public OpenAPI openAPI(){

        return new OpenAPI().info(new Info().title("Gestão de Vagas").description("API responsável pela gestão de vagas")
                .version("1"))
                .schemaRequirement("jwt_auth",createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme(){
        return new SecurityScheme().name("jwt_auth").scheme("bearer").bearerFormat("JWT")
                .type(SecurityScheme.Type.HTTP);
    }
}
