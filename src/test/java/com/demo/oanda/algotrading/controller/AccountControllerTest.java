package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.AccountService;
import com.oanda.v20.account.Account;
import com.oanda.v20.account.AccountProperties;
import com.oanda.v20.account.AccountSummary;
import com.oanda.v20.primitives.Instrument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testGetAccountDetails() throws Exception {
        Account account = new Account();
        when(accountService.getAccountDetails(anyString())).thenReturn(account);

        mockMvc.perform(get("/account/{accountId}", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetAccounts() throws Exception {
        List<AccountProperties> accounts = Collections.singletonList(new AccountProperties());
        when(accountService.getAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    public void testGetAccountInstruments() throws Exception {
        List<Instrument> instruments = Collections.singletonList(new Instrument());
        when(accountService.getAccountInstruments(anyString())).thenReturn(instruments);

        mockMvc.perform(get("/account/{accountId}/instruments", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    public void testGetAccountSummary() throws Exception {
        AccountSummary accountSummary = new AccountSummary();
        when(accountService.getAccountSummary(anyString())).thenReturn(accountSummary);

        mockMvc.perform(get("/account/{accountId}/summary", "12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}