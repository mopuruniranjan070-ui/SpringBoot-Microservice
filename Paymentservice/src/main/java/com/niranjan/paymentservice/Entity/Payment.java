package com.niranjan.paymentservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paymentdb")
public class Payment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderid;
    private long userid;
    private String status;
    private String transactionId;
}
