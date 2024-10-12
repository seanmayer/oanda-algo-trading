package com.demo.oanda.algotrading.config;

import java.util.logging.Logger;

import com.oanda.v20.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OandaConfig {

    private static final Logger logger = Logger.getLogger(OandaConfig.class.getName());
    private final String apiKey;
    private final String url;

    public OandaConfig() {
        this.apiKey = System.getenv("OANDA_API_KEY");
        this.url = "https://api-fxpractice.oanda.com";
        if (this.apiKey == null) {
            logger.severe("OANDA_API_KEY environment variable is not set.");
        }
        if (this.url == null) {
            logger.severe("OANDA_API_URL environment variable is not set.");
        }
    }

    @Bean
    public Context context() {
        String token = getApiKey();
        String url = getUrl();
        return new Context(url, token);
    }

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }
}