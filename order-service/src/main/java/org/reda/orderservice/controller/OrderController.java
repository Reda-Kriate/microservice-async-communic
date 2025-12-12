package org.reda.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.reda.orderservice.dto.OrderRequest;
import org.reda.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "circuitRD" , fallbackMethod = "fallBackMeth")
    @TimeLimiter(name = "circuitRD")
    @Retry(name = "circuitRD")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        String token = orderService.extractToken();
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest,token));
    }

    public CompletableFuture<String> fallBackMeth(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Something went wrong, please try later! - " + runtimeException.getMessage());
    }

}
