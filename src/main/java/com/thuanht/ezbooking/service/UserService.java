package com.thuanht.ezbooking.service;

import com.thuanht.ezbooking.dto.request.SignUpRequest;
import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.exception.AppException;
import com.thuanht.ezbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Tạo người dùng mới
    public User createUser(SignUpRequest userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail()))
            throw new AppException.EmailAlreadyRegisteredException();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String passwordHash = passwordEncoder.encode(userRequest.getPassword());

        User user = User.builder()
                .fullName(userRequest.getFullName())
                .email(userRequest.getEmail())
                .password(passwordHash)
                .firebase_uid("")
                .phoneNumber("")
                .dob("")
                .gender("")
                .avatarUrl("")
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }


    public User getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(AppException.UserNotFoundException::new);
    }



    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Cập nhật thông tin người dùng
    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setFullName(updatedUser.getFullName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setDob(updatedUser.getDob());
            user.setGender(updatedUser.getGender());
            user.setAvatarUrl(updatedUser.getAvatarUrl());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    // Xóa người dùng
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Tìm người dùng theo email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}