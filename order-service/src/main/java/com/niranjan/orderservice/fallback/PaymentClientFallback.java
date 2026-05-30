package com.niranjan.orderservice.fallback;

import com.niranjan.commonlib.Dto.PaymentDto;
import com.niranjan.orderservice.client.PaymentClient;
import org.springframework.stereotype.Component;

@Component
public class PaymentClientFallback implements PaymentClient {

    @Override
    public PaymentDto processpayment(PaymentDto paymentDto, String token) {
        throw new RuntimeException("Payment Service unavailable while processing payment for orderId="
                + paymentDto.getOrderId());
    }
}