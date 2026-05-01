package com.daniels.springboot.events;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Cacheable(value = "events", key = "#id")
    public Event getEventById(int id) {
        System.out.println("HITTING DB");
        return eventRepository.findById(id).orElse(null);
    }
}
