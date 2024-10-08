package com.thuanht.ezbooking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantResponse {
    private Long p_id;
    private Long event_id; // Only include IDs
    private Long user_id;
    private LocalDateTime joinedAt;

}
