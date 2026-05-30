package com.niranjan.commonlib.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;        // orderId
    private String productName;
    private String email;
    private Long userId;    // ✅ link to User
}
