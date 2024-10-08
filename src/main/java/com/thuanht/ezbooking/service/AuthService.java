package com.thuanht.ezbooking.service;


import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.repository.AuthRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    public static String SIGNER_KEY = "b15cc2a6a69c57e73b62faad7a59d3f70a32a3f17185ab6c7a499208ff9aaea9";

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String verifyToken(String token) {
        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            // Phân tích JWT
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Xác minh JWT
            boolean verified = signedJWT.verify(verifier);

            Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (verified && expirationDate.after(new Date())) {
                JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
                System.out.println(claimsSet.getSubject().toString());
                return claimsSet.getSubject();
            } else {
                throw new Exception("Token is not valid or has expired");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<User> getUserByFirebaseUid(String firebaseID) {
        return authRepository.findByEmailOrIdOrFirebaseUid(firebaseID, firebaseID, firebaseID);
    }
//    public APIResponse<LoginResponse> getDataAfterVerified(String token) {
//        String id = verifyToken(token);
//        User user = authRepository.findByEmailOrIdOrFirebaseUid(id, id, id).orElseThrow(AppException.UserNotFoundException::new);
//
//        String firebaseUid = Optional.ofNullable(user.getFirebase_uid()).orElse("");
//        String fullName = Optional.ofNullable(user.getFullName()).orElse("N/A");
//        String email = Optional.ofNullable(user.getEmail()).orElse("N/A");
//        String dob = Optional.ofNullable(user.getDob()).orElse("");
//        String gender = Optional.ofNullable(user.getGender()).orElse("");
//        String avatarUrl = Optional.ofNullable(user.getAvatarUrl()).orElse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7gTERsv3nO-4I-R9C00Uor_m_nmxT0sE9Cg&s");
//        String phoneNumber = Optional.ofNullable(user.getPhoneNumber()).orElse("Unknown");
//
//        LoginResponse loginResponse = LoginResponse.builder()
//                .id(user.getId())
//                .firebase_uid(firebaseUid)
//                .fullName(fullName)
//                .email(email)
//                .dob(dob)
//                .gender(gender)
//                .avatarUrl(avatarUrl)
//                .phoneNumber(phoneNumber)
//                .build();
//
//        return APIResponse.<LoginResponse>builder()
//                .success(true)
//                .message("Get user successfully")
//                .data(loginResponse)
//                .build();
//    }
}
