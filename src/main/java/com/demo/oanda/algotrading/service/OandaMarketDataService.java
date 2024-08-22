package com.demo.oanda.algotrading.service;

import com.demo.oanda.algotrading.config.OandaConfig;
import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.primitives.InstrumentName;
import com.oanda.v20.instrument.CandlestickGranularity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OandaMarketDataService {

    private final Context context;
    private final String token;

    public OandaMarketDataService(OandaConfig oandaConfig) {
        String url = "https://api-fxpractice.oanda.com";
        this.token = oandaConfig.getApiKey();
        context = new Context(url, token);
        context.setHeader("Authorization", "Bearer " + token);
    }

    public List<Candlestick> getMarketData(String instrument) throws ExecuteException, RequestException {
        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                .setGranularity(CandlestickGranularity.M1)
                .setCount(10L);

        InstrumentCandlesResponse response = context.instrument.candles(request);
        return response.getCandles();
    }
}