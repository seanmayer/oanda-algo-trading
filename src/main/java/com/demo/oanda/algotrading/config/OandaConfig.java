package com.demo.oanda.algotrading.config;

import java.util.logging.Logger;

import com.oanda.v20.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OandaConfig {

    private static final Logger logger = Logger.getLogger(OandaConfig.class.getName());
    private String apiKey;

    public OandaConfig() {
        this.apiKey = System.getenv("OANDA_API_KEY");
        if (this.apiKey == null) {
            logger.severe("OANDA_API_KEY environment variable is not set.");
        }
    }

    @Bean
    public Context context() {
        String url = "https://api-fxpractice.oanda.com";
        String token = getApiKey();
        return new Context(url, token);
    }

    public String getApiKey() {
        return apiKey;
    }
}
