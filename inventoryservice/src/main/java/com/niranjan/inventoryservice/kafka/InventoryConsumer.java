package com.niranjan.inventoryservice.kafka;

import com.niranjan.commonlib.Dto.InventoryCheckEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryConsumer {

    @KafkaListener(topics = "inventory-check-topic",groupId = "inventory-service-group")
    public void ConsumeInventoryCheckEvent(InventoryCheckEvent event){
        System.out.println("📦 Received inventory check for product: " + event.getProductName());

        // Check stock logic
        boolean inStock = true; // Replace with actual DB check

        if (inStock) {
            System.out.println("✅ Stock available for product: " + event.getProductName());
            // You can later publish a response event to Kafka (e.g., InventoryCheckedEvent)
        } else {
            System.out.println("❌ Stock unavailable for product: " + event.getProductName());
        }
    }
    }

