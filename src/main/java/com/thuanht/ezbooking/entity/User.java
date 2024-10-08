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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long user_id;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String firebase_uid = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String fullName = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String phoneNumber = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String email = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String password = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String dob = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String gender = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String avatarUrl = "";

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private LocalDateTime createdAt = LocalDateTime.now(); // or set this in a prePersist method
}

