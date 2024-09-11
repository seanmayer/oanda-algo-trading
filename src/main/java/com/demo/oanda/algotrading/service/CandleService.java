package com.demo.oanda.algotrading.service;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentCandlesRequest;
import com.oanda.v20.instrument.InstrumentCandlesResponse;
import com.oanda.v20.primitives.InstrumentName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CandleService {

    private static final Logger logger = Logger.getLogger(CandleService.class.getName());
    private final Context context;

    public CandleService(Context context) {
        this.context = context;
    }

    // Fetch candlestick data for a specific instrument
    public List<Candlestick> getCandlestickData(String instrument, CandlestickGranularity granularity, int count)
            throws ExecuteException, RequestException {
        try {
            InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                    .setGranularity(granularity)
                    .setCount((long) count);

            InstrumentCandlesResponse response = context.instrument.candles(request);
            logger.info("Received candlestick data from Oanda API: " + response);
            return response.getCandles();
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching candlestick data: " + e.getMessage());
            throw e;
        }
    }
}

