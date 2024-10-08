package com.thuanht.ezbooking.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class APIResponse<T> {
    boolean success;
    String message;
    T data;
}
