package com.daniels.tickets.events;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;
import org.springframework.web.ErrorResponse;
import org.springframework.http.HttpStatus;

@RestController
public class EventController {

    private final OrganizerRepository organizerRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;

    public EventController(OrganizerRepository organizerRepository, EventRepository eventRepository, ProductRepository productRepository) {
        this.organizerRepository = organizerRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
    }
    @GetMapping(path = "/organizers")
    public List<Organizer> getOrganizers(){
        return organizerRepository.findAll();
    }
    @GetMapping(path = "/events")
    public List<Event> getEventsByOrganizers(@RequestParam("organizerId") int organizerId){
        return eventRepository.findByOrganizerId(organizerId);
    }    
    @GetMapping(path = "/events/{id}")
    public Event getEventById(@PathVariable("id") int eventId){
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new NoSuchElementException("Event with id " + eventId + " not found"));
    }
    @GetMapping(path = "/products")
    public List<Product> getProductsByEvent(@RequestParam("eventId") int eventId){
        return productRepository.findByEventId(eventId);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse notFound(NoSuchElementException ex) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
