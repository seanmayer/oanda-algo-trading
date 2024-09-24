package com.demo.oanda.algotrading.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI oandaTradingOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("OANDA Trading API")
                        .description("API documentation for OANDA trading services")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Sean Mayer")
                                .email("not-implemented@scalablehuman.com")
                                .url("https://scalablehuman.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("OANDA API Documentation")
                        .url("https://developer.oanda.com/rest-live-v20/"));
    }
}

