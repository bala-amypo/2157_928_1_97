package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Server configuration (same style as OpenApiConfig)
                .servers(List.of(
                        new Server().url("https://9497.pro604cr.amypo.ai/")
                ))
                // Swagger Info
                .info(new Info()
                        .title("Demo API")
                        .version("1.0")
                        .description("API documentation for Demo project"));
    }
}
