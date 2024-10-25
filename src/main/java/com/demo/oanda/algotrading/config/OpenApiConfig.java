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

    private final String title;
    private final String description;
    private final String version;
    private final String contactName;
    private final String contactEmail;
    private final String contactUrl;
    private final String externalDocsDescription;
    private final String externalDocsUrl;

    public OpenApiConfig(
            @Value("${openapi.title}") String title,
            @Value("${openapi.description}") String description,
            @Value("${openapi.version}") String version,
            @Value("${openapi.contact.name}") String contactName,
            @Value("${openapi.contact.email}") String contactEmail,
            @Value("${openapi.contact.url}") String contactUrl,
            @Value("${openapi.externalDocs.description}") String externalDocsDescription,
            @Value("${openapi.externalDocs.url}") String externalDocsUrl) {
        this.title = title;
        this.description = description;
        this.version = version;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactUrl = contactUrl;
        this.externalDocsDescription = externalDocsDescription;
        this.externalDocsUrl = externalDocsUrl;
    }

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