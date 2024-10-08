package com.thuanht.ezbooking.exception;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }

    // Exception khi email đã được đăng ký
    public static class EmailAlreadyRegisteredException extends AppException {
        public EmailAlreadyRegisteredException() {
            super("Email đã được đăng ký.");
        }
    }

    // Exception khi người dùng không tìm thấy
    public static class UserNotFoundException extends AppException {
        public UserNotFoundException() {
            super("Người dùng không tìm thấy.");
        }
    }

    // Exception khi mật khẩu không đúng
    public static class InvalidPasswordException extends AppException {
        public InvalidPasswordException() {
            super("Mật khẩu không đúng.");
        }
    }

    public static class ResourceNotFoundException extends AppException {
        public ResourceNotFoundException(String message) {
            super("Event not found");
        }
    }

    public static class UserAlreadyAttendException extends AppException {
        public UserAlreadyAttendException(String message) {
            super("User has attend.");
        }
    }

    // Exception when an event is not found
    public static class EventNotFoundException extends AppException {
        public EventNotFoundException(Long eventId) {
            super("Event with ID " + eventId + " not found.");
        }
    }

    // Exception when tickets are sold out
    public static class TicketsSoldOutException extends AppException {
        public TicketsSoldOutException(Long eventId) {
            super("Tickets for event with ID " + eventId + " are sold out.");
        }
    }

    // Exception when a ticket is not found
    public static class TicketNotFoundException extends AppException {
        public TicketNotFoundException(Long ticketId) {
            super("Ticket with ID " + ticketId + " not found.");
        }
    }

    // Exception when booking is invalid
    public static class InvalidBookingException extends AppException {
        public InvalidBookingException(String message) {
            super(message);
        }
    }
}
