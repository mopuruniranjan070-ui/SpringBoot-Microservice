package com.niranjan.orderservice.Kafka;

import com.niranjan.commonlib.Dto.InventoryCheckEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {

    private final KafkaTemplate<String, InventoryCheckEvent> kafkaTemplate;

    public InventoryProducer(KafkaTemplate<String, InventoryCheckEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryCheckEvent(InventoryCheckEvent event) {
        kafkaTemplate.send("inventory-check-topic", event);
        System.out.println("📤 Sent inventory check event for product: " + event.getProductName());
    }
}

