package com.niranjan.userservice.Service;

import com.niranjan.commonlib.Dto.OrderCreatedEvent;
import com.niranjan.userservice.Entity.AppUser;
import com.niranjan.userservice.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventConsumer {

    @Autowired
    private UserRepository userRepository;

    @KafkaListener(topics = "order-created-topic", groupId = "user-service")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.println("Consumed event: " + event);
        AppUser user = userRepository.findById(event.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + event.getUserId()));

        user.setOrderCount(user.getOrderCount() + 1);
        userRepository.save(user);
    }
}