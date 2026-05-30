package com.niranjan.paymentservice.Controller;

import com.niranjan.commonlib.Dto.PaymentDto;
import com.niranjan.paymentservice.Entity.Payment;
import com.niranjan.paymentservice.Mapper.PaymentMapper;
import com.niranjan.paymentservice.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @PostMapping
    public PaymentDto processpayment(@RequestBody PaymentDto paymentDto) {

        if(Math.random()>0.2){
            paymentDto.setStatus("SUCCESS");
            paymentDto.setTransactionId(UUID.randomUUID().toString());

        }else {
            paymentDto.setStatus("FAILURE");
            paymentDto.setTransactionId(UUID.randomUUID().toString());
        }
        Payment entity = paymentMapper.toEntity(paymentDto);
        Payment saved = paymentRepository.save(entity); // ✅ use save, not merge
        return paymentMapper.toDto(saved);

    }
}
