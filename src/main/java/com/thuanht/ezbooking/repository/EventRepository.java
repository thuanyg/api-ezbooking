package com.thuanht.ezbooking.repository;

import com.thuanht.ezbooking.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {
    List<Event> findByNameContaining(String name);

    List<Event> findByLocationContaining(String location);

    List<Event> findByEventType(String eventType);
}