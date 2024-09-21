package com.demo.oanda.algotrading.service;

import com.oanda.v20.Context;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.account.AccountID;
import com.oanda.v20.order.*;
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

    // Method to get the list of orders for a specific account
    public OrderListResponse getOrders(String accountId) throws ExecuteException, RequestException {
        try {
            AccountID accountID = new AccountID(accountId);
            OrderListResponse response = context.order.list(accountID);
            logger.info("Successfully retrieved orders for account " + accountId);
            return response;
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching orders for account: " + e.getMessage());
            throw e;
        }
    }

    public OrderListPendingResponse getPendingOrders(String accountId) throws ExecuteException, RequestException {
        try {
            AccountID accountID = new AccountID(accountId);
            OrderListPendingResponse response = context.order.listPending(accountID);
            logger.info("Successfully retrieved pending orders for account " + accountId);
            return response;
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching pending orders for account: " + e.getMessage());
            throw e;
        }
    }

    // Fetch a specific order by its orderSpecifier for a given account
    public OrderGetResponse getOrder(String accountId, String orderSpecifier) throws ExecuteException, RequestException {
        try {
            AccountID accountID = new AccountID(accountId);
            OrderSpecifier orderID = new OrderSpecifier(orderSpecifier);
            OrderGetResponse response = context.order.get(accountID, orderID);
            logger.info("Successfully retrieved order " + orderSpecifier + " for account " + accountId);
            return response;
        } catch (RequestException e) {
            logger.severe("RequestException occurred while fetching order: " + e.getMessage());
            throw e;
        }
    }

}
