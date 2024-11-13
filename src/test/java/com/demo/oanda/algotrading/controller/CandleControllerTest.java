package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.CandleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.oanda.v20.instrument.Candlestick;
import com.oanda.v20.instrument.CandlestickGranularity;
import com.oanda.v20.instrument.InstrumentOrderBookResponse;
import com.oanda.v20.instrument.InstrumentPositionBookResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CandleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CandleService candleService;

    @InjectMocks
    private CandleController candleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(InstrumentOrderBookResponse.class, new MockSerializer<>(InstrumentOrderBookResponse.class));
        module.addSerializer(InstrumentPositionBookResponse.class, new MockSerializer<>(InstrumentPositionBookResponse.class));
        objectMapper.registerModule(module);
        StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(candleController);
        builder.setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper));
        mockMvc = builder.build();
    }

    @Test
    public void testGetCandlestickData() throws Exception {
        List<Candlestick> candlesticks = Collections.singletonList(new Candlestick());
        when(candleService.getCandlestickData(anyString(), eq(CandlestickGranularity.M1), eq(10))).thenReturn(candlesticks);

        mockMvc.perform(get("/instruments/{instrument}/candles", "EUR_USD")
                        .param("granularity", "M1")
                        .param("count", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{}]"));
    }

    @Test
    public void testGetOrderBook() throws Exception {
        InstrumentOrderBookResponse orderBookResponse = Mockito.mock(InstrumentOrderBookResponse.class);
        when(candleService.getOrderBook(anyString())).thenReturn(orderBookResponse);

        mockMvc.perform(get("/instruments/{instrument}/orderBook", "EUR_USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testGetPositionBook() throws Exception {
        InstrumentPositionBookResponse positionBookResponse = Mockito.mock(InstrumentPositionBookResponse.class);
        when(candleService.getPositionBook(anyString())).thenReturn(positionBookResponse);

        mockMvc.perform(get("/instruments/{instrument}/positionBook", "EUR_USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }
}