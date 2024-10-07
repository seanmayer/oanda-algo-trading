package com.demo.oanda.algotrading.service;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.instrument.*;
import com.oanda.v20.primitives.InstrumentName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CandleService {

    private static final Logger logger = Logger.getLogger(CandleService.class.getName());
    private final Context context;

    public CandleService(final Context context) {
        this.context = context;
    }

    // Fetch candlestick data for a specific instrument
    public List<Candlestick> getCandlestickData(final String instrument, final CandlestickGranularity granularity, final int count)
            throws ExecuteException, RequestException {
        try {
            final InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                    .setGranularity(granularity)
                    .setCount((long) count);

            final InstrumentCandlesResponse response = context.instrument.candles(request);
            logger.info("Received candlestick data from Oanda API: " + response);
            return response.getCandles();
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while fetching candlestick data: " + e.getMessage());
            throw e;
        }
    }

    // Fetch order book data for a specific instrument
    public InstrumentOrderBookResponse getOrderBook(final String instrument) throws ExecuteException, RequestException {
        try {
            final InstrumentOrderBookRequest request = new InstrumentOrderBookRequest(new InstrumentName(instrument));
            final InstrumentOrderBookResponse response = context.instrument.orderBook(request);
            logger.info("Received order book data from Oanda API: " + response);
            return response;
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while fetching order book data: " + e.getMessage());
            throw e;
        }
    }

    // Fetch position book data for a specific instrument
    public InstrumentPositionBookResponse getPositionBook(final String instrument) throws ExecuteException, RequestException {
        try {
            final InstrumentPositionBookRequest request = new InstrumentPositionBookRequest(new InstrumentName(instrument));
            final InstrumentPositionBookResponse response = context.instrument.positionBook(request);
            logger.info("Received position book data from Oanda API: " + response);
            return response;
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while fetching position book data: " + e.getMessage());
            throw e;
        }
    }
}