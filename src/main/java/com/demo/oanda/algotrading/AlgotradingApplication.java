package com.demo.oanda.algotrading;

import com.demo.oanda.algotrading.config.OandaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OandaConfig.class)
public class AlgotradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgotradingApplication.class, args);
	}


}
