package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.CandleService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentOrderBookResponse;
import com.oanda.v20.instrument.InstrumentPositionBookResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandleController {

    private final CandleService candleService;

    public CandleController(final CandleService candleService) {
        this.candleService = candleService;
    }

    @Operation(summary = "Get Candlestick Data", description = "Get candlestick data for the specified instrument")
    @GetMapping("/instruments/{instrument}/candles")
    public List<Candlestick> getCandlestickData(
            @PathVariable final String instrument,
            @RequestParam(defaultValue = "M1") final CandlestickGranularity granularity,
            @RequestParam(defaultValue = "10") final int count)
            throws ExecuteException, RequestException {
        return candleService.getCandlestickData(instrument, granularity, count);
    }

    @Operation(summary = "Get Order Book", description = "Get order book data for the specified instrument")
    @GetMapping("/instruments/{instrument}/orderBook")
    public InstrumentOrderBookResponse getOrderBook(@PathVariable final String instrument) throws ExecuteException, RequestException {
        return candleService.getOrderBook(instrument);
    }

    @Operation(summary = "Get Position Book", description = "Get position book data for the specified instrument")
    @GetMapping("/instruments/{instrument}/positionBook")
    public InstrumentPositionBookResponse getPositionBook(@PathVariable final String instrument) throws ExecuteException, RequestException {
        return candleService.getPositionBook(instrument);
    }

}