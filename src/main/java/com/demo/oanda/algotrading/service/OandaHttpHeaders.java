package com.demo.oanda.algotrading.service;

import org.springframework.http.HttpHeaders;

public class OandaHttpHeaders {

    public static HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}