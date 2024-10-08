package com.thuanht.ezbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Reference to the User entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;  // Reference to the Ticket entity

    @NotNull
    private Double amount;  // Total payment amount

    @Column(nullable = false)
    private LocalDateTime paymentDate;  // Date and time of payment

    @Column(nullable = false)
    private String paymentMethod;  // Payment method (e.g., credit card, PayPal)

    @Column(nullable = false)
    private String status;  // Payment status (e.g., completed, pending, failed)

    @PrePersist
    protected void onCreate() {
        this.paymentDate = LocalDateTime.now();  // Automatically set the payment date on creation
    }

    // Getters and Setters omitted for brevity
}