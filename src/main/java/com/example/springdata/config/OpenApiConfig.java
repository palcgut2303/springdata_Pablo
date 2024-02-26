package com.example.springdata.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
  http://localhost:8070/v3/api-docs
  http://localhost:8070/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Empresa API Pablo")
                        .description("API REST de la empresa PABLO S.L.")
                        .version("v1")
                        .license(new License().name("Linux 2.0").url("https://www.example.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Wiki Docs")
                        .url("https://www.example.com")
                );


    }

}
