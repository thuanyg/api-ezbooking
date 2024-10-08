package com.thuanht.ezbooking;

import com.thuanht.ezbooking.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EventSpecification {

    public static Specification<Event> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name != null ? criteriaBuilder.like(root.get("name"), "%" + name + "%") : null;
    }

    public static Specification<Event> hasLocation(String location) {
        return (root, query, criteriaBuilder) ->
                location != null ? criteriaBuilder.like(root.get("location"), "%" + location + "%") : null;
    }

    public static Specification<Event> hasEventType(String eventType) {
        return (root, query, criteriaBuilder) ->
                eventType != null ? criteriaBuilder.equal(root.get("eventType"), eventType) : null;
    }

    public static Specification<Event> hasEventDate(LocalDate eventDate) {
        return (root, query, criteriaBuilder) ->
                eventDate != null ? criteriaBuilder.equal(root.get("eventDate"), eventDate) : null;
    }
}
