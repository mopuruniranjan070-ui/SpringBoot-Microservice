package com.niranjan.orderservice.fallback;

import com.niranjan.commonlib.Dto.InventoryDto;
import com.niranjan.orderservice.client.InventoryClient;
import org.springframework.stereotype.Component;

@Component
public class InventoryClientFallback implements InventoryClient {
    @Override
    public InventoryDto getstock(String productNames, String token) {
        throw new RuntimeException("Inventory Service unavailable while fetching stock for product: " + productNames);
    }

    @Override
    public InventoryDto reduceStock(String productNames, String token) {
        throw new RuntimeException("Inventory Service unavailable while reducing stock for product: " + productNames);
    }
}