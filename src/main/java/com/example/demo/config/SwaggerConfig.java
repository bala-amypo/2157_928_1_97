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
                .servers(List.of(
                        new Server().url("https://9497.pro604cr.amypo.ai") // your project server URL
                ))
                .info(new Info()
                        .title("Digital Certificate Generator API")  // <-- change the title
                        .version("1.0")                             // version of your API
                        .description("API documentation for Digital Certificate Generator project") // description
                );
    }
}
