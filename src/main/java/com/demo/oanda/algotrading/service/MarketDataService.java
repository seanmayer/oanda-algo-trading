package com.demo.oanda.algotrading.service;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.primitives.InstrumentName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MarketDataService {

    private static final Logger logger = Logger.getLogger(MarketDataService.class.getName());
    private final Context context;

    public MarketDataService(Context context) {
        this.context = context;
    }

    public List<Candlestick> getMarketData(String instrument) throws ExecuteException, RequestException {
        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                .setGranularity(CandlestickGranularity.M1)  // Adjust the granularity as needed
                .setCount(10L);  // Adjust the count as needed

        try {
            InstrumentCandlesResponse response = context.instrument.candles(request);
            logger.info("Received response from Oanda API: " + response);
            return response.getCandles();
        } catch (RequestException e) {
            logger.severe("RequestException occurred: " + e.getMessage());
            throw e;
        }
    }
}