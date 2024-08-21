package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.OandaMarketDataService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketDataController {

    private final OandaMarketDataService marketDataService;

    public MarketDataController(OandaMarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping("/marketdata/{instrument}")
    public List<Candlestick> getMarketData(@PathVariable String instrument) throws ExecuteException, RequestException {
        return marketDataService.getMarketData(instrument);
    }
}
