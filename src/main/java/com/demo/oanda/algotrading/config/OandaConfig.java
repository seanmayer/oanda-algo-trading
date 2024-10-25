package com.demo.oanda.algotrading.config;

import java.util.logging.Logger;

import com.oanda.v20.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OandaConfig {

    private static final Logger logger = Logger.getLogger(OandaConfig.class.getName());

    private final String apiKey;
    private final String url;

    public OandaConfig(@Value("${oanda.api.key}") String apiKey, @Value("${oanda.api.url}") String url) {
        this.apiKey = apiKey;
        this.url = url;
    }

    @Bean
    public Context context() {
        if (this.apiKey == null) {
            logger.severe("OANDA_API_KEY environment variable is not set.");
        }
        if (this.url == null) {
            logger.severe("OANDA_API_URL environment variable is not set.");
        }
        return new Context(url, apiKey);
    }

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }
}