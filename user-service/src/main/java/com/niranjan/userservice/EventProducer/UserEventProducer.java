package com.niranjan.userservice.EventProducer;

import com.niranjan.commonlib.Dto.UserCreatedEvent;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserEventProducer {
    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public UserEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        kafkaTemplate.send("users-topic", event);
        System.out.println("Published UserCreatedEvent: " + event);
    }
}