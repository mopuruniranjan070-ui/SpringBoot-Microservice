package com.niranjan.paymentservice.Mapper;

import com.niranjan.commonlib.Dto.PaymentDto;
import com.niranjan.paymentservice.Entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentDto toDto(Payment payment) {
        return new PaymentDto(payment.getId(),payment.getOrderid(),payment.getUserid(),payment.getStatus(),payment.getTransactionId());
    }

    public Payment toEntity(PaymentDto dto) {
        Payment payment = new Payment();
        // DO NOT set payment.setId(dto.getId()) for new payments
        payment.setOrderid(dto.getOrderId());
        payment.setUserid(dto.getUserid());
        payment.setStatus(dto.getStatus());
        payment.setTransactionId(dto.getTransactionId());
        return payment;
    }

}
