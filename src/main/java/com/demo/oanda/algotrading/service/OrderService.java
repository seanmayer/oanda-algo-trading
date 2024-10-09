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

    public OrderService(final Context context) {
        this.context = context;
    }

    public OrderCreateResponse createOrder(final String accountId, final MarketOrderRequest orderRequest) throws ExecuteException, RequestException {
        try {
            // Create the OrderCreateRequest object
            final OrderCreateRequest request = new OrderCreateRequest(new AccountID(accountId))
                    .setOrder(orderRequest);

            // Execute the order creation request
            final OrderCreateResponse response = context.order.create(request);
            logger.info("Order created successfully for account " + accountId + ": " + response);
            return response;
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while creating order: " + e.getMessage());
            throw e;
        }
    }

    // Method to get the list of orders for a specific account
    public OrderListResponse getOrders(final String accountId) throws ExecuteException, RequestException {
        try {
            final AccountID accountID = new AccountID(accountId);
            final OrderListResponse response = context.order.list(accountID);
            logger.info("Successfully retrieved orders for account " + accountId);
            return response;
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while fetching orders for account: " + e.getMessage());
            throw e;
        }
    }

    public OrderListPendingResponse getPendingOrders(final String accountId) throws ExecuteException, RequestException {
        try {
            final AccountID accountID = new AccountID(accountId);
            final OrderListPendingResponse response = context.order.listPending(accountID);
            logger.info("Successfully retrieved pending orders for account " + accountId);
            return response;
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while fetching pending orders for account: " + e.getMessage());
            throw e;
        }
    }

    // Fetch a specific order by its orderSpecifier for a given account
    public OrderGetResponse getOrder(final String accountId, final String orderSpecifier) throws ExecuteException, RequestException {
        try {
            final AccountID accountID = new AccountID(accountId);
            final OrderSpecifier orderID = new OrderSpecifier(orderSpecifier);
            final OrderGetResponse response = context.order.get(accountID, orderID);
            logger.info("Successfully retrieved order " + orderSpecifier + " for account " + accountId);
            return response;
        } catch (final RequestException e) {
            logger.severe("RequestException occurred while fetching order: " + e.getMessage());
            throw e;
        }
    }

}