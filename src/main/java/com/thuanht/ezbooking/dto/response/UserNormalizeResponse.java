package com.thuanht.ezbooking.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNormalizeResponse {
    Long id;
    String firebase_uid;
    String fullName;
    String phoneNumber;
    String email;
    String dob;
    String gender;
    String avatarUrl;
}
