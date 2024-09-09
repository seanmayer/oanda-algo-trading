package com.demo.oanda.algotrading.service;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.*;
import com.oanda.v20.primitives.Instrument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AccountService {

    private static final Logger logger = Logger.getLogger(AccountService.class.getName());
    private final Context context;

    public AccountService(Context context) {
        this.context = context;
    }

    public List<AccountProperties> getAccounts() throws ExecuteException, RequestException {
        try {
            AccountListResponse response = context.account.list();
            logger.info("Received accounts response from Oanda API: " + response);
            return response.getAccounts();
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching accounts: " + e.getMessage());
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

    public AccountSummary getAccountSummary(String accountId) throws ExecuteException, RequestException {
        try {
            AccountSummaryResponse response = context.account.summary(new AccountID(accountId));
            logger.info("Received account summary from Oanda API: " + response);
            return response.getAccount();
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching account summary: " + e.getMessage());
            throw e;
        }
    }

    public List<Instrument> getAccountInstruments(String accountId) throws ExecuteException, RequestException {
        try {
            AccountInstrumentsResponse response = context.account.instruments(new AccountID(accountId));
            logger.info("Received instruments response from Oanda API: " + response);
            return response.getInstruments();
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching instruments: " + e.getMessage());
            throw e;
        }
    }
}