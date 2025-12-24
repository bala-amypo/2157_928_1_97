@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Certificate Generator API")
                        .version("1.0")
                        .description("API documentation for Digital Certificate Generator project")
                );
    }
}
