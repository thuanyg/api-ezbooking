package com.thuanht.ezbooking.service;

import com.thuanht.ezbooking.dto.request.PaymentRequest;
import com.thuanht.ezbooking.dto.response.PaymentResponse;
import com.thuanht.ezbooking.entity.Payment;
import com.thuanht.ezbooking.entity.Ticket;
import com.thuanht.ezbooking.entity.User;
import com.thuanht.ezbooking.repository.PaymentRepository;
import com.thuanht.ezbooking.repository.TicketRepository;
import com.thuanht.ezbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new payment based on the request DTO
    public PaymentResponse createPayment(PaymentRequest paymentRequestDTO) {
        User user = userRepository.findById(paymentRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = ticketRepository.findById(paymentRequestDTO.getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setTicket(ticket);
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPaymentMethod(paymentRequestDTO.getPaymentMethod());
        payment.setStatus("pending");  // Default status

        Payment savedPayment = paymentRepository.save(payment);

        // Return a response DTO
        return mapToPaymentResponseDTO(savedPayment);
    }

    // Convert Payment entity to PaymentResponseDTO
    private PaymentResponse mapToPaymentResponseDTO(Payment payment) {
        return new PaymentResponse(
                payment.getPaymentId(),
                payment.getUser().getUser_id(),
                payment.getTicket().getTicketId(),
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getStatus()
        );
    }

}
