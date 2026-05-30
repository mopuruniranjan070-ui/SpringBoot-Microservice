package com.niranjan.orderservice.client;

import com.niranjan.commonlib.Dto.PaymentDto;
import com.niranjan.orderservice.fallback.PaymentClientFallback;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service",fallback = PaymentClientFallback.class )
public interface PaymentClient {

    @RateLimiter(name = "paymentServiceRateLimiter")
    @TimeLimiter(name="paymentServiceTimeLimiter")
    @Retry(name = "paymentServiceRetry")
    @CircuitBreaker(name ="paymentServiceCircuitBreaker")
    @PostMapping("/payments")
    PaymentDto processpayment(@RequestBody PaymentDto paymentDto,
                              @RequestHeader("Authorization") String token);

}
