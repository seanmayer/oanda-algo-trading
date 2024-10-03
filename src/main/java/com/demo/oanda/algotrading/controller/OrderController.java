package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.OrderService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.order.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Order Management", description = "APIs for managing orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Create an Order", description = "Create an order for the specified account")
    @PostMapping("/accounts/{accountId}/orders")
    public OrderCreateResponse createOrder(
            @PathVariable final String accountId,
            @RequestBody final MarketOrderRequest orderRequest) throws ExecuteException, RequestException {
        return orderService.createOrder(accountId, orderRequest);
    }

    @Operation(summary = "Get Orders", description = "List all orders in the account")
    @GetMapping("/accounts/{accountId}/orders")
    public OrderListResponse getOrders(
            @PathVariable final String accountId) throws ExecuteException, RequestException {
        return orderService.getOrders(accountId);
    }

    @Operation(summary = "Get Pending Orders", description = "List all pending orders in the account")
    @GetMapping("/accounts/{accountId}/pendingOrders")
    public OrderListPendingResponse getPendingOrders(
            @PathVariable final String accountId) throws ExecuteException, RequestException {
        return orderService.getPendingOrders(accountId);
    }

    @Operation(summary = "Get Order", description = "Get details of a specific order")
    @GetMapping("/accounts/{accountId}/orders/{orderSpecifier}")
    public OrderGetResponse getOrder(
            @PathVariable final String accountId,
            @PathVariable final String orderSpecifier) throws ExecuteException, RequestException {
        return orderService.getOrder(accountId, orderSpecifier);
    }

}