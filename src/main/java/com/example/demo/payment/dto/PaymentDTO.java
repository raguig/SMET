package com.example.demo.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    private Long id;
    private BigDecimal amount;
    private String currency;
    private String method;      // e.g. CARD, PAYPAL, CASH
    private String status;      // e.g. PENDING, SUCCESS, FAILED
    private Long userId;        // optional link to user
    private String provider;    // which provider handled the payment
    private LocalDateTime createdAt;
}


