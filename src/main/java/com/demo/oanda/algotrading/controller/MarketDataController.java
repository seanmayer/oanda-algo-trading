package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.AccountService;
import com.demo.oanda.algotrading.service.MarketDataService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.Account;
import com.oanda.v20.account.AccountProperties;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.primitives.Instrument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketDataController {

    private final MarketDataService marketDataService;
    private final AccountService accountService;

    public MarketDataController(MarketDataService marketDataService, AccountService accountService) {
        this.marketDataService = marketDataService;
        this.accountService = accountService;
    }

    @GetMapping("/marketdata/{instrument}")
    public List<Candlestick> getMarketData(@PathVariable String instrument) throws ExecuteException, RequestException {
        return marketDataService.getMarketData(instrument);
    }

    @GetMapping("/account/{accountId}")
    public Account getAccountDetails(@PathVariable String accountId) throws ExecuteException, RequestException {
        return accountService.getAccountDetails(accountId);
    }

    @GetMapping("/accounts")
    public List<AccountProperties> getAccounts() throws ExecuteException, RequestException {
        return accountService.getAccounts();
    }

    @GetMapping("/account/{accountId}/instruments")
    public List<Instrument> getAccountInstruments(@PathVariable String accountId) throws ExecuteException, RequestException {
        return accountService.getAccountInstruments(accountId);
    }

    @GetMapping("/account/{accountId}/summary")
    public AccountSummary getAccountSummary(@PathVariable String accountId) throws ExecuteException, RequestException {
        return accountService.getAccountSummary(accountId);
    }

}