package com.thuanht.ezbooking.controller;

import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody String token) {
        String firebaseUid = authService.verifyToken(token);
        if (firebaseUid != null) {
            // Use firebase_uid to get user information
            Optional<User> user = authService.getUserByFirebaseUid(firebaseUid);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(404).body("User not found.");
            }
        } else {
            return ResponseEntity.status(401).body("Token is invalid or expired.");
        }
    }

}
