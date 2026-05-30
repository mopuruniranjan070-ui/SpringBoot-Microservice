package com.niranjan.orderservice.client;

import com.niranjan.commonlib.Dto.InventoryDto;
import com.niranjan.orderservice.fallback.InventoryClientFallback;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="inventory-service",fallback = InventoryClientFallback.class)
public interface InventoryClient {

    @TimeLimiter(name = "inventoryServiceTimeLimiter")
    @RateLimiter(name = "inventoryServiceRateLimiter")
    @Retry(name = "inventoryServiceRetry")
    @CircuitBreaker(name = "inventoryServiceCircuitBreaker")
    @GetMapping("/inventory/{productName}")
    InventoryDto getstock(@PathVariable("productName") String productNames, @RequestHeader("Authorization") String token);

    @TimeLimiter(name = "inventoryServiceTimeLimiter")
    @RateLimiter(name = "inventoryServiceRateLimiter")
    @Retry(name = "inventoryServiceRetry")
    @CircuitBreaker(name = "inventoryServiceCircuitBreaker")
    @PutMapping("/inventory/{productName}/reduce")
    InventoryDto reduceStock(@PathVariable("productName") String productNames,
                             @RequestHeader("Authorization") String token);



}
