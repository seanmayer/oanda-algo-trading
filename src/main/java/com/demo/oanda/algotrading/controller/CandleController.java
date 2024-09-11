package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.CandleService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandleController {

    private final CandleService candleService;

    public CandleController(CandleService candleService) {
        this.candleService = candleService;
    }

    @GetMapping("/instruments/{instrument}/candles")
    public List<Candlestick> getCandlestickData(
            @PathVariable String instrument,
            @RequestParam(defaultValue = "M1") CandlestickGranularity granularity,
            @RequestParam(defaultValue = "10") int count)
            throws ExecuteException, RequestException {
        return candleService.getCandlestickData(instrument, granularity, count);
    }
}

