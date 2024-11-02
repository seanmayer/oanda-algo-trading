package com.demo.oanda.algotrading.config;

import com.oanda.v20.Context;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "oanda.api.key=testApiKey",
        "oanda.api.url=http://testurl.com"
})
public class OandaConfigTest {

    @Value("${oanda.api.key}")
    private String apiKey;

    @Value("${oanda.api.url}")
    private String url;

    @Test
    public void testContext() throws NoSuchFieldException, IllegalAccessException {
        OandaConfig oandaConfig = new OandaConfig(apiKey, url);
        Context context = oandaConfig.context();

        assertNotNull(context);

        // Access private fields using reflection
        java.lang.reflect.Field uriField = context.getClass().getDeclaredField("uri");
        uriField.setAccessible(true);
        assertEquals(url, uriField.get(context));

        java.lang.reflect.Field tokenField = context.getClass().getDeclaredField("token");
        tokenField.setAccessible(true);
        assertEquals(apiKey, tokenField.get(context));
    }

    @Test
    public void testGetUrl() {
        OandaConfig oandaConfig = new OandaConfig(apiKey, url);
        assertEquals(url, oandaConfig.getUrl());
    }

    @Test
    public void testGetApiKey() {
        OandaConfig oandaConfig = new OandaConfig(apiKey, url);
        assertEquals(apiKey, oandaConfig.getApiKey());
    }
}