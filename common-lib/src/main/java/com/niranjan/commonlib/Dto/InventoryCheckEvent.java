package com.niranjan.commonlib.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class InventoryCheckEvent {
    private String productName;
    private Long userId;
    private String email;
    public InventoryCheckEvent() {}
    public InventoryCheckEvent(String productName, Long userId, String email) {
        this.productName = productName;
        this.userId = userId;
        this.email = email;
    }
}
