package com.daniels.springboot.events;

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
import java.util.Collections;

@RestController
public class EventController {

    private final OrganizerRepository organizerRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;
    private final EncodingService service;

    public EventController(OrganizerRepository organizerRepository,
            EventRepository eventRepository,
            ProductRepository productRepository,
            EncodingService service) {
        this.organizerRepository = organizerRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
        this.service = service;
    }

    @GetMapping(path = "/organizers")
    public ResponseEntity<Map<String, Object>> getOrganizers(
            @RequestParam(name = "cursor", required = false) String cursor,
            @RequestParam(name = "limit", required = false, defaultValue = "3") int limit,
            HttpServletRequest request) {
        System.out.println("Received cursor: " + cursor + ", limit: " + limit);
        if(limit < 1) {
            limit = 3;
        }
        int cursorId = 0;
        if (cursor != null && !cursor.isEmpty()) {
            cursorId = service.decode(cursor);
            cursorId -= 100;
        }
        System.out.println("Decoded cursor: " + cursorId);
        Map<String, Object> response = new HashMap<>();
        List<Organizer> organizerList = organizerRepository.CursorFindById(cursorId + 100, limit + 1);
        System.out.println("Fetched organizers: " + organizerList.size());
        if (organizerList == null)
            organizerList = Collections.emptyList();

        String nextCursor = null;
        String url = request.getRequestURL().toString().split("\\?")[0];
        String next = null;
        if (!organizerList.isEmpty() && organizerList.size() > limit) {
            nextCursor = service.encode("" + organizerList.get(organizerList.size() - 2).getId());
            next = String.format("%s?&cursor=%s&limit=%d", url, nextCursor, limit);
        }
        organizerList = organizerList.stream().limit(limit).toList();
        response.put("organizers", organizerList);
        response.put("next", next);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/events")
    public ResponseEntity<Map<String, Object>> getEventsByOrganizer(@RequestParam("organizerId") int organizerId,
            @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(name = "limit", required = false, defaultValue = "3") int limit,
            HttpServletRequest request) {
        if (offset < 1) {
            offset = 0;
        }

        if (limit < 1) {
            limit = 3;
        }
        String url = request.getRequestURL().toString().split("\\?")[0];
        String prev;
        String next = String.format("%s?organizerId=%d&offset=%d&limit=%d", url, organizerId, offset + limit, limit);
        if (offset > 0) {
            prev = String.format("%s?organizerId=%d&offset=%d&limit=%d", url, organizerId, Math.max(0, offset - limit),
                    limit);
        } else {
            prev = null;
        }
        Map<String, Object> response = new HashMap<>();
        Optional<List<Event>> events = Optional.of(eventRepository.pagedFindByOrganizerId(organizerId, offset, limit));
        response.put("events", events.get());
        response.put("next", next);
        response.put("prev", prev);
        // response.put("total", eventRepository.count());
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
