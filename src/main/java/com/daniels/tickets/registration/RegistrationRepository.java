package com.daniels.tickets.registration;

import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

@Repository
public class RegistrationRepository {
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger() ;
    private final Map<String, Registration> registrationsByTicketCode = new ConcurrentHashMap<>();
    
    public Registration create(Registration registration) {
        int id = ID_GENERATOR.incrementAndGet();
        String ticketCode = UUID.randomUUID().toString();
        var saved = new Registration(id, registration.productId(), ticketCode, registration.attendeeName ());
        registrationsByTicketCode.put(ticketCode, saved);
        return saved;
    }
    public Optional<Registration> findByTicketCode(String ticketCode) {
        return Optional.ofNullable(registrationsByTicketCode.get(ticketCode));
    }
    public Optional<Registration> update(Registration registration) {
    String ticketCode = registration.ticketCode();

    // Check if registration exists
    if (registrationsByTicketCode.containsKey(ticketCode)) {
        // Replace old registration with new one
        registrationsByTicketCode.put(ticketCode, registration);
        return Optional.of(registration);
    }

    // Not found
    return Optional.empty();
}
    public Optional<Registration> deleteByTicketCode(String ticketCode) {
        return Optional.ofNullable(registrationsByTicketCode.remove(ticketCode));
    }
    
}
