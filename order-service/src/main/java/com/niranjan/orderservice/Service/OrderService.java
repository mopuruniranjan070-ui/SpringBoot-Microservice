package com.niranjan.orderservice.Service;


import com.niranjan.commonlib.Dto.*;
import com.niranjan.orderservice.Kafka.InventoryProducer;
import com.niranjan.orderservice.Kafka.OrderProducer;
import com.niranjan.orderservice.client.InventoryClient;
import com.niranjan.orderservice.client.PaymentClient;
import com.niranjan.orderservice.client.UserClient;
import com.niranjan.orderservice.entity.OrderUser;
import com.niranjan.orderservice.exception.InvalidRequestException;
import com.niranjan.orderservice.exception.Paymentfailed;
import com.niranjan.orderservice.repository.OrderRepository;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private  final OrderRepository orderRepository;
    private final InventoryProducer orderProducer;
    private final OrderProducer orderCreatedEvent;
    private final UserClient userClient;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;


    public OrderService(OrderRepository orderRepository, InventoryProducer orderProducer, UserClient userClient, InventoryClient inventoryClient,
                        PaymentClient paymentClient, OrderProducer orderCreatedEvent) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.userClient = userClient;
        this.orderCreatedEvent = orderCreatedEvent;
        this.inventoryClient = inventoryClient;
        this.paymentClient = paymentClient;
    }

    public OrderUser createOrder(Long userId, String productNames, String email,String token) {
        AppUserDto appUser = userClient.getUserById(userId,token); // unwrap future

        if (appUser.getName().equals("Unknown")) {
            throw new InvalidRequestException("Cannot create order: User not available");
        }

//        InventoryDto inventory = inventoryClient.getstock(productNames,token);
//        if (inventory.getStock()<=0) {
//            throw new InvalidRequestException("Cannot create order: Product not available");
//        }
//        inventoryClient.reduceStock(productNames,token);
        InventoryCheckEvent event = new InventoryCheckEvent(productNames,userId,email);
        orderProducer.sendInventoryCheckEvent(event);
        OrderUser orderUser = new OrderUser();
        orderUser.setUserId(userId);
        orderUser.setProductName(productNames);
        orderUser.setEmail(email);
        orderRepository.save(orderUser);
OrderCreatedEvent orderCreated = new OrderCreatedEvent(
        orderUser.getId(),
        orderUser.getUserId(),
        orderUser.getProductName(),
        orderUser.getEmail()
);
        orderCreatedEvent.sendOrderCreated(orderCreated);
        System.out.println("✅ Order event published to Kafka for product: " + productNames);


        PaymentDto paymentDto = PaymentDto.builder()
                .orderId(orderUser.getId())   // generated after save
                .userid(userId)
                .status("PENDING")
                .build();
        PaymentDto result = paymentClient.processpayment(paymentDto,token);
        if ("FAILED".equals(result.getStatus())) {
            throw new Paymentfailed("Cannot create order");

        }
        // create event
return orderUser;
    }
}
