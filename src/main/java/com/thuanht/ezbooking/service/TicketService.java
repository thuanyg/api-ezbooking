package com.thuanht.ezbooking.service;

import com.thuanht.ezbooking.entity.Ticket;
import com.thuanht.ezbooking.exception.AppException;
import com.thuanht.ezbooking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException.TicketNotFoundException(ticketId));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Long ticketId, Ticket ticketDetails) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setPrice(ticketDetails.getPrice());
        ticket.setStatus(ticketDetails.getStatus());
        ticket.setUpdatedAt(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);
        ticketRepository.delete(ticket);
    }
}
