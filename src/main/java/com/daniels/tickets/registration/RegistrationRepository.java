package com.daniels.tickets.registration;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
/*import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.NoSuchElementException;
import jakarta.validation.Valid;*/

public interface RegistrationRepository extends MongoRepository<Registration, String>{

   Optional<Registration> findByTicketCode(String ticketCode);

   void deleteByTicketCode(String ticketCode);
}

