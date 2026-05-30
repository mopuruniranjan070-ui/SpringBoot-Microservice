package com.niranjan.commonlib.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private long id;
    private long orderId;
    private long userid;
    private String status;
    private String transactionId;


}
