package com.thuanht.ezbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long p_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;  // Reference to the Event entity

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Reference to the User entity

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private LocalDateTime joinedAt = LocalDateTime.now();
}
