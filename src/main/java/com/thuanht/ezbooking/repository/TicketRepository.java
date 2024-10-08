package com.thuanht.ezbooking.repository;

import com.thuanht.ezbooking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
