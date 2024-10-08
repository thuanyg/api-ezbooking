package com.thuanht.ezbooking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;  // Reference to the Event entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Reference to the User entity

    @NotNull
    private Integer quantity = 1;  // Number of tickets booked

    @NotNull
    private Double price;  // Price per ticket

    @NotNull
    @Column(length = 20)
    private String status;  // Ticket status: available, sold, refunded

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // Ticket creation timestamp

    @Column(nullable = false)
    private LocalDateTime updatedAt;  // Last update timestamp

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}