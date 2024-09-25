package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.AccountService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.Account;
import com.oanda.v20.account.AccountProperties;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.primitives.Instrument;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get Account Details", description = "Get details of a specific account")
    @GetMapping("/account/{accountId}")
    public Account getAccountDetails(@PathVariable String accountId) throws ExecuteException, RequestException {
        return accountService.getAccountDetails(accountId);
    }

    @Operation(summary = "Get Accounts", description = "List all accounts")
    @GetMapping("/accounts")
    public List<AccountProperties> getAccounts() throws ExecuteException, RequestException {
        return accountService.getAccounts();
    }

    @Operation(summary = "Get Account Instruments", description = "List all instruments available for a specific account")
    @GetMapping("/account/{accountId}/instruments")
    public List<Instrument> getAccountInstruments(@PathVariable String accountId) throws ExecuteException, RequestException {
        return accountService.getAccountInstruments(accountId);
    }

    @Operation(summary = "Get Account Summary", description = "Get a summary of a specific account")
    @GetMapping("/account/{accountId}/summary")
    public AccountSummary getAccountSummary(@PathVariable String accountId) throws ExecuteException, RequestException {
        return accountService.getAccountSummary(accountId);
    }
}