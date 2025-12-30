package com.example.demo.payment.provider;

import com.example.demo.payment.dto.PaymentDTO;

public interface PaymentProvider {

    /**
     * Human-readable provider name (e.g., "MockPay").
     */
    String getName();

    /**
     * Indicates whether this provider is currently available to process payments.
     */
    boolean isAvailable();

    /**
     * Process a payment request. Implementations can enrich the request with status or other metadata.
     */
    PaymentDTO pay(PaymentDTO request);
}


