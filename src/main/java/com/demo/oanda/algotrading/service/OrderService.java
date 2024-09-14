package com.demo.oanda.algotrading.service;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateRequest;
import com.oanda.v20.order.OrderCreateResponse;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderService.class.getName());
    private final Context context;

    public OrderService(Context context) {
        this.context = context;
    }

    public OrderCreateResponse createOrder(String accountId, MarketOrderRequest orderRequest) throws ExecuteException, RequestException {
        try {
            // Create the OrderCreateRequest object
            OrderCreateRequest request = new OrderCreateRequest(new AccountID(accountId))
                    .setOrder(orderRequest);

            // Execute the order creation request
            OrderCreateResponse response = context.order.create(request);
            logger.info("Order created successfully for account " + accountId + ": " + response);
            return response;
        } catch (RequestException e) {
            logger.severe("RequestException occurred while creating order: " + e.getMessage());
            throw e;
        }
    }
}
