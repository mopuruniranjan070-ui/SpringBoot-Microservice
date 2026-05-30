package com.niranjan.orderservice.client;

import com.niranjan.commonlib.Dto.AppUserDto;
import com.niranjan.orderservice.fallback.UserClientFallback;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service",fallback = UserClientFallback.class)
public interface UserClient {
    @TimeLimiter(name = "userServiceTimeLimiter")
    @RateLimiter(name = "userServiceRateLimiter")
    @Retry(name = "userServiceRetry")
    @CircuitBreaker(name = "userServiceCircuitBreaker")
    @GetMapping("users/{id}")
    AppUserDto getUserById(@PathVariable ("id") Long id, @RequestHeader("Authorization") String token);


}


