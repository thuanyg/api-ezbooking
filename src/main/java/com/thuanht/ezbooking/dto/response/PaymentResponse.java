package com.thuanht.ezbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {
    private Long paymentId;  // The ID of the payment
    private Long userId;  // The user making the payment
    private Long ticketId;  // The ticket associated with the payment
    private Double amount;  // The payment amount
    private LocalDateTime paymentDate;  // Date and time of payment
    private String paymentMethod;  // Payment method (e.g., credit card, PayPal)
    private String status;  // Payment status (e.g., completed, pending, failed)
}
