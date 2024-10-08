package com.thuanht.ezbooking.controller;


import com.thuanht.ezbooking.dto.response.ParticipantResponse;
import com.thuanht.ezbooking.entity.Participant;
import com.thuanht.ezbooking.service.ParticipantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @PostMapping
    public ResponseEntity<Participant> createParticipant(@RequestBody Map<String, Long> request) {
        Long eventId = request.get("event_id");
        Long userId = request.get("user_id");

        Participant createdParticipant = participantService.addParticipant(eventId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipant(@PathVariable Long id) {
        Participant participant = participantService.getParticipantById(id);
        return participant != null ? ResponseEntity.ok(participant) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ParticipantResponse>> getAllParticipants() {
        List<ParticipantResponse> participants = participantService.getAllParticipants();
        return ResponseEntity.ok(participants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participant) {
        Participant updatedParticipant = participantService.updateParticipant(id, participant);
        return updatedParticipant != null ? ResponseEntity.ok(updatedParticipant) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteParticipantByUserAndEvent(@RequestParam("user_id") Long userId,
                                                                  @RequestParam("event_id") Long eventId) {
        try {
            // Call the service to remove the participant
            participantService.deleteParticipantByUserAndEvent(eventId, userId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Participant not found for given user and event.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing the participant.");
        }
    }
}
