package com.thuanht.ezbooking.repository;

import com.thuanht.ezbooking.entity.Participant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Participant p WHERE p.user.user_id = :userId AND p.event.eventId = :eventId")
    boolean existsByUserAndEvent(@Param("userId") Long userId, @Param("eventId") Long eventId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Participant p WHERE p.user.user_id = :userId AND p.event.eventId = :eventId")
    void deleteByUserAndEvent(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
