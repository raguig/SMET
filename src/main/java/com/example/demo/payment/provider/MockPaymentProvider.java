package com.example.demo.payment.provider;

import com.example.demo.payment.dto.PaymentDTO;
import org.springframework.stereotype.Component;

@Component
public class MockPaymentProvider implements PaymentProvider {

    @Override
    public String getName() {
        return "MockPay";
    }

    @Override
    public boolean isAvailable() {
        // Always available in mock
        return true;
    }

    @Override
    public PaymentDTO pay(PaymentDTO request) {
        // Mock a successful payment
        return PaymentDTO.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .method(request.getMethod() != null ? request.getMethod() : getName())
                .status("SUCCESS")
                .provider(getName())
                .userId(request.getUserId())
                .build();
    }
}


