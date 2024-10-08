package com.thuanht.ezbooking.service;

import com.thuanht.ezbooking.EventSpecification;
import com.thuanht.ezbooking.entity.Event;
import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.exception.AppException;
import com.thuanht.ezbooking.repository.EventRepository;
import com.thuanht.ezbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new AppException.EventNotFoundException(eventId));
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long eventId, Event updatedEvent) {
        return eventRepository.findById(eventId).map(event -> {
            event.setName(updatedEvent.getName());
            event.setLocation(updatedEvent.getLocation());
            event.setEventType(updatedEvent.getEventType());
            event.setDescription(updatedEvent.getDescription());
            event.setDate(updatedEvent.getDate());
            event.setTicketPrice(updatedEvent.getTicketPrice());
            event.setAvailableTickets(updatedEvent.getAvailableTickets());
            event.setImageUrls(updatedEvent.getImageUrls());
            event.setVideoUrl(updatedEvent.getVideoUrl());
            event.setAdditionalInfo(updatedEvent.getAdditionalInfo());
            return eventRepository.save(event);
        }).orElse(null);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public ResponseEntity<String> joinEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new AppException.ResourceNotFoundException("Event not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException.ResourceNotFoundException("User not found"));

        eventRepository.save(event);

        return ResponseEntity.ok("User added to event successfully");
    }


    public List<Event> searchEvents(String name, String location, String eventType, LocalDate eventDate) {
        Specification<Event> specification = Specification.where(
                        EventSpecification.hasName(name))
                .and(EventSpecification.hasLocation(location))
                .and(EventSpecification.hasEventType(eventType))
                .and(EventSpecification.hasEventDate(eventDate));

        return eventRepository.findAll(specification);
    }
}