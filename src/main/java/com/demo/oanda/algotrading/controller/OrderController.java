package com.demo.oanda.algotrading.controller;

import com.demo.oanda.algotrading.service.OrderService;
import com.oanda.v20.ExecuteException;
import com.oanda.v20.RequestException;
import com.oanda.v20.order.MarketOrderRequest;
import com.oanda.v20.order.OrderCreateResponse;
import com.oanda.v20.order.OrderListPendingResponse;
import com.oanda.v20.order.OrderListResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/accounts/{accountId}/orders")
    public OrderCreateResponse createOrder(
            @PathVariable String accountId,
            @RequestBody MarketOrderRequest orderRequest) throws ExecuteException, RequestException {
        return orderService.createOrder(accountId, orderRequest);
    }

    @GetMapping("/accounts/{accountId}/orders")
    public OrderListResponse getOrders(
            @PathVariable String accountId) throws ExecuteException, RequestException {
        return orderService.getOrders(accountId);
    }

    @GetMapping("/accounts/{accountId}/pendingOrders")
    public OrderListPendingResponse getPendingOrders(
            @PathVariable String accountId) throws ExecuteException, RequestException {
        return orderService.getPendingOrders(accountId);
    }

}
