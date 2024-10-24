package com.demo.oanda.algotrading.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${openapi.title}")
    private String title;

    @Value("${openapi.description}")
    private String description;

    @Value("${openapi.version}")
    private String version;

    @Value("${openapi.contact.name}")
    private String contactName;

    @Value("${openapi.contact.email}")
    private String contactEmail;

    @Value("${openapi.contact.url}")
    private String contactUrl;

    @Value("${openapi.externalDocs.description}")
    private String externalDocsDescription;

    @Value("${openapi.externalDocs.url}")
    private String externalDocsUrl;

    @Bean
    public OpenAPI oandaTradingOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(title)
                        .description(description)
                        .version(version)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail)
                                .url(contactUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(externalDocsDescription)
                        .url(externalDocsUrl));
    }
}