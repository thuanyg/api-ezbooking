package com.thuanht.ezbooking.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserNormalizeRequest {
    String id;
    String firebase_uid;
    String fullName;
    String phoneNumber;
    String email;
    String dob;
    String gender;
    String avatarUrl;
}
