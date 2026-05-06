package com.daniels.springboot.events;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class EventService {
    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Cacheable(value = "events", key = "#id")
    public Event getEventById(int id) {
        System.out.println("GETTING EVENT FROM DB");
        return eventRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Event with id " + id + " not found"));
    }

    @CacheEvict(value = "events", key = "#id")
    public Event updateEvent(int id, String newName) {
        System.out.println(" UPDATING DB");
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event with id " + id + " not found"));
        event.setName(newName);
        return eventRepository.save(event);
    }

    @CacheEvict(value = "events", key = "#id")
    public void deleteEvent(int id) {
        System.out.println(" DELETING FROM DB");
        if (!eventRepository.existsById(id)) {
            throw new NoSuchElementException("Event with id " + id + " not found");
        }
        eventRepository.deleteById(id);
    }

}

