package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.MarketDataService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketDataController {

    private final MarketDataService marketDataService;

    public MarketDataController(final MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @Operation(summary = "Get Market Data", description = "Get market data for the specified instrument")
    @GetMapping("/marketdata/{instrument}")
    public List<Candlestick> getMarketData(@PathVariable final String instrument) throws ExecuteException, RequestException {
        return marketDataService.getMarketData(instrument);
    }
}