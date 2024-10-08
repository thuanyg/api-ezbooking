package com.thuanht.ezbooking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentRequest {
    @NotNull
    private Long userId;  // The user making the payment

    @NotNull
    private Long ticketId;  // The ticket being paid for

    @NotNull
    private Double amount;  // The payment amount

    @NotNull
    private String paymentMethod;  // The payment method (e.g., credit card, PayPal)
}
