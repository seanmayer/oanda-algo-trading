package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.MarketDataService;
import com.oanda.v20.instrument.Candlestick;
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

public class MarketDataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MarketDataService marketDataService;

    @InjectMocks
    private MarketDataController marketDataController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(marketDataController).build();
    }

    @Test
    public void testGetMarketData() throws Exception {
        List<Candlestick> candlesticks = Collections.singletonList(new Candlestick());
        when(marketDataService.getMarketData(anyString())).thenReturn(candlesticks);

        mockMvc.perform(get("/marketdata/{instrument}", "EUR_USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }
}