package com.thuanht.ezbooking.controller;

import com.thuanht.ezbooking.dto.request.SignUpRequest;
import com.thuanht.ezbooking.dto.response.APIResponse;
import com.thuanht.ezbooking.dto.response.UserNormalizeResponse;
import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Tạo người dùng mới
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody SignUpRequest user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    // Lấy thông tin người dùng theo ID
    @GetMapping("/{userId}")
    public APIResponse<UserNormalizeResponse> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserNormalizeResponse userResponse = UserNormalizeResponse.builder()
                .id(userId)
                .firebase_uid(user.getFirebase_uid())
                .dob(user.getDob())
                .avatarUrl(user.getAvatarUrl())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .build();
        return APIResponse.<UserNormalizeResponse>builder()
                .success(true)
                .message("Get user successfully")
                .data(userResponse)
                .build();
    }

    // Lấy tất cả người dùng
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Cập nhật thông tin người dùng
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    // Xóa người dùng
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }

    // Tìm người dùng theo email
    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
