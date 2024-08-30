package com.demo.oanda.algotrading.service;

import com.demo.oanda.algotrading.config.OandaConfig;
import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.Account;
import com.oanda.v20.account.AccountGetResponse;
import com.oanda.v20.account.AccountID;
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
        logger.info("Using API token: Bearer " + token);
    }

    public List<Candlestick> getMarketData(String instrument) throws ExecuteException, RequestException {
        InstrumentCandlesRequest request = new InstrumentCandlesRequest(new InstrumentName(instrument))
                .setGranularity(CandlestickGranularity.M1) // Adjust the granularity as needed
                .setCount(10L); // Adjust the count as needed

        try {
            InstrumentCandlesResponse response = context.instrument.candles(request);
            logger.info("Received response from Oanda API: " + response);
            return response.getCandles();
        } catch (RequestException e) {
            logger.severe("RequestException occurred: " + e.getMessage());
            throw e;
        }
    }

    public Account getAccountDetails(String accountId) throws ExecuteException, RequestException {
        try {
            AccountGetResponse accountResponse = context.account.get(new AccountID(accountId));
            logger.info("Received account response from Oanda API: " + accountResponse);
            return accountResponse.getAccount();
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching account details: " + e.getMessage());
            throw e;
        }
    }
}
