package com.example.demo.payment.service;

import com.example.demo.payment.dto.PaymentDTO;
import com.example.demo.payment.model.Payment;
import com.example.demo.payment.persistence.PaymentRepository;
import com.example.demo.payment.provider.PaymentProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final List<PaymentProvider> paymentProviders;

    @Transactional
    public PaymentDTO createPayment(PaymentDTO request) {
        PaymentProvider provider = paymentProviders.stream()
                .filter(PaymentProvider::isAvailable)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No payment provider available"));

        // Delegate to provider
        PaymentDTO processed = provider.pay(request);

        Payment payment = Payment.builder()
                .amount(processed.getAmount())
                .currency(processed.getCurrency())
                .method(processed.getMethod() != null ? processed.getMethod() : provider.getName())
                .status(processed.getStatus() != null ? processed.getStatus() : "PENDING")
                .userId(processed.getUserId())
                .createdAt(LocalDateTime.now())
                .build();

        Payment saved = paymentRepository.save(payment);

        return PaymentDTO.builder()
                .id(saved.getId())
                .amount(saved.getAmount())
                .currency(saved.getCurrency())
                .method(saved.getMethod())
                .status(saved.getStatus())
                .userId(saved.getUserId())
                .provider(provider.getName())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public PaymentDTO getPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .currency(payment.getCurrency())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .userId(payment.getUserId())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}


