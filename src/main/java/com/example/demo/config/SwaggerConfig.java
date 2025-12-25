package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Digital certificate generator")
                                .version("1.0")
                                .description("Digital certificate genarator APIs"))
                .addServersItem(new Server()
                        .url("https://9171.408procr.amypo.ai/")
                        .description("Custom Server URL"));
    }
}
