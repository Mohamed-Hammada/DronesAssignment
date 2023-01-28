package com.musala.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiZainConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info().title("Drones Assignment")
                .description("AutoGenerated API Documentation for the Drones Assignment")
                .termsOfService("")
                .license(new License().name("Dummy License")
                        .url("https://musala.com")).version("Version 1.0")
                .contact(new Contact().name("MUSALA").url("https://musala.com").email("info@musala.com")));
    }

}