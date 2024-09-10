package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.AccountService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.Account;
import com.oanda.v20.account.AccountProperties;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.primitives.Instrument;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
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