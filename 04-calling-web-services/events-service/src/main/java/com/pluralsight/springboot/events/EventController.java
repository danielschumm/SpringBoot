package com.pluralsight.springboot.events;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EventController {

    private final OrganizerRepository organizerRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;

    public EventController(OrganizerRepository organizerRepository,
                           EventRepository eventRepository,
                           ProductRepository productRepository) {
        this.organizerRepository = organizerRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/organizers")
    public List<Organizer> getOrganizers() {
        return organizerRepository.findAll();
    }

    @GetMapping(path = "/events")
    public ResponseEntity<Map<String, Object>> getEventsByOrganizer(@RequestParam("organizerId") int organizerId,
                    @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
                    @RequestParam(name = "limit", required = false, defaultValue = "3") int limit,
                    HttpServletRequest request) {
        if(offset < 1) {
         offset = 0;
        }

        if(limit < 1) {
            limit = 3;
        }

        String url = request.getRequestURL().toString().split("\\?")[0];
        String prev;
        String next = String.format("%s?organizerId=%d&offset=%d&limit=%d", url, organizerId, offset + limit, limit);


        if(offset > 0) {
            prev = String.format("%s?organizerId=%d&offset=%d&limit=%d", url, organizerId, Math.max(0, offset - limit), limit);
        }else {
            prev = null;    
        }
        Map<String, Object> response = new HashMap<>();
        Optional<List<Event>> events = Optional.of(eventRepository.pagedFindByOrganizerId(organizerId, offset, limit));
        response.put("events", events.get());
        response.put("next", next);
        response.put("prev", prev);
        //response.put("total", eventRepository.count());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/events/{id}")
    public Event getEventById(@PathVariable("id") int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " not found"));
    }

    @GetMapping(path = "/products")
    public List<Product> getProductsByEvent(@RequestParam("eventId") int eventId) {
        return productRepository.findByEventId(eventId);
    }

    @GetMapping(path = "/products/{id}")
    public Product getProductById(@PathVariable("id") int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product with id " + productId + " not found"));
    }
}
