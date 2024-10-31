package com.demo.oanda.algotrading.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "openapi.title=Test API",
        "openapi.description=Test API Description",
        "openapi.version=1.0",
        "openapi.contact.name=Test Contact",
        "openapi.contact.email=test@example.com",
        "openapi.contact.url=http://example.com",
        "openapi.externalDocs.description=Test External Docs",
        "openapi.externalDocs.url=http://example.com/docs"
})
public class OpenApiConfigTest {

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

    @Test
    public void testOandaTradingOpenAPI() {
        OpenApiConfig openApiConfig = new OpenApiConfig(
                title, description, version, contactName, contactEmail, contactUrl, externalDocsDescription, externalDocsUrl
        );

        OpenAPI openAPI = openApiConfig.oandaTradingOpenAPI();

        assertNotNull(openAPI);
        assertEquals(title, openAPI.getInfo().getTitle());
        assertEquals(description, openAPI.getInfo().getDescription());
        assertEquals(version, openAPI.getInfo().getVersion());

        Contact contact = openAPI.getInfo().getContact();
        assertNotNull(contact);
        assertEquals(contactName, contact.getName());
        assertEquals(contactEmail, contact.getEmail());
        assertEquals(contactUrl, contact.getUrl());

        ExternalDocumentation externalDocs = openAPI.getExternalDocs();
        assertNotNull(externalDocs);
        assertEquals(externalDocsDescription, externalDocs.getDescription());
        assertEquals(externalDocsUrl, externalDocs.getUrl());
    }
}