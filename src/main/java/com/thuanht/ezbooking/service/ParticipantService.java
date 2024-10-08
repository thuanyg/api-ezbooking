package com.thuanht.ezbooking.service;

import com.thuanht.ezbooking.dto.response.ParticipantResponse;
import com.thuanht.ezbooking.entity.Event;
import com.thuanht.ezbooking.entity.Participant;
import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.exception.AppException;
import com.thuanht.ezbooking.repository.EventRepository;
import com.thuanht.ezbooking.repository.ParticipantRepository;
import com.thuanht.ezbooking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public Participant addParticipant(Long eventId, Long userId) {
        // Fetch the Event and User from the database
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new AppException.EventNotFoundException(eventId));

        User user = userRepository.findById(userId)
                .orElseThrow(AppException.UserNotFoundException::new);

        if(participantRepository.existsByUserAndEvent(userId, eventId)){
            throw new AppException.UserAlreadyAttendException("This user already attend");
        }

        // Create a new Participant
        Participant participant = new Participant();
        participant.setEvent(event);
        participant.setUser(user);
        participant.setJoinedAt(LocalDateTime.now());

        // Save the Participant
        return participantRepository.save(participant);
    }

    public Participant getParticipantById(Long id) {
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.orElse(null); // Handle not found scenario as needed
    }

    public List<ParticipantResponse> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return participants.stream().map(participant -> {
            ParticipantResponse dto = new ParticipantResponse();
            dto.setP_id(participant.getP_id());
            dto.setEvent_id(participant.getEvent().getEventId()); // Assuming Event has a getId() method
            dto.setUser_id(participant.getUser().getUser_id()); // Assuming User has a getId() method
            dto.setJoinedAt(participant.getJoinedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    public Participant updateParticipant(Long id, Participant participant) {
        if (!participantRepository.existsById(id)) {
            return null; // Handle not found scenario
        }
        participant.setP_id(id); // Set the ID for the participant being updated
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }

    public void deleteParticipantByUserAndEvent(Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new AppException.EventNotFoundException(eventId));

        User user = userRepository.findById(userId)
                .orElseThrow(AppException.UserNotFoundException::new);


        participantRepository.deleteByUserAndEvent(userId, eventId);
    }

}
