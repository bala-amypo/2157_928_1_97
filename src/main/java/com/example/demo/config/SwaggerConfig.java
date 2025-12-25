package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://9171.408procr.amypo.ai/").description("Local server"))
                .info(new Info()
                        .title("Digital Certificate Generator API")
                        .version("1.0")
                        .description("API for managing digital certificates"));
    }
}