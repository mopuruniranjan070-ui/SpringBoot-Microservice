package com.niranjan.inventoryservice.Service;

import com.niranjan.commonlib.Dto.InventoryDto;
import com.niranjan.inventoryservice.Entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryDto toDto(Inventory inventory) {
       return new InventoryDto(inventory.getId(),inventory.getProductname(),inventory.getStock());
    }

    public Inventory toEntity(InventoryDto inventoryDto) {
        return new Inventory(inventoryDto.getId(),inventoryDto.getProductname(),inventoryDto.getStock());
    }
}
