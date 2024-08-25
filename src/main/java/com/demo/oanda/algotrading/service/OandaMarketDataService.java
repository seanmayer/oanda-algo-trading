package com.demo.oanda.algotrading.service;

import com.demo.oanda.algotrading.config.OandaConfig;
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
public class OandaMarketDataService {

    private static final Logger logger = Logger.getLogger(OandaMarketDataService.class.getName());
    private final Context context;

    public OandaMarketDataService(OandaConfig oandaConfig) {
        String url = "https://api-fxpractice.oanda.com";
        String token = oandaConfig.getApiKey();
        this.context = new Context(url, token);
    }

    public List<Candlestick> getMarketData(String instrument) throws ExecuteException, RequestException {
        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                .setGranularity(CandlestickGranularity.M1)
                .setCount(10L);

        logger.info("Sending request to Oanda API: " + request);
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