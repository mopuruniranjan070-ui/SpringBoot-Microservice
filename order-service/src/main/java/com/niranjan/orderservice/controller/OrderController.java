package com.niranjan.orderservice.controller;

import com.niranjan.commonlib.Dto.OrderCreatedEvent;
import com.niranjan.commonlib.Dto.OrderDto;
import com.niranjan.orderservice.Kafka.OrderProducer;

import com.niranjan.orderservice.Service.OrderService;
import com.niranjan.orderservice.entity.OrderUser;

import com.niranjan.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderUser> createOrder(@RequestBody OrderUser orderUser, @RequestHeader("Authorization") String Authheader) {
        String token = Authheader.substring(7);
     OrderUser orderuse = orderService.createOrder(orderUser.getUserId(),orderUser.getProductName(),orderUser.getEmail(),token);

     return ResponseEntity.ok(orderuse);
    }

    @GetMapping("/{id}")
    public OrderUser getOrderById(@RequestBody OrderUser order) {
        return orderRepository.findById(order.getId()).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}