package com.daniels.tickets.events;

import org.springframework.stereotype.Repository; 
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public class EventRepository {
    private final List<Event> events = List.of(
        new Event (501, "Glubonantics Tech Conference",
            new Organizer (101, "Globoiantius", "Glubumemlies Technology Corporation"),
            new Venue (201, "Glubunatics Main Office", "Test Street 325", "New York", "USA"),
            LocalDate.of(2023, 10, 2), LocalDate.of(2023, 10, 4)),
        new Event (502, "Globomantics Developer Day",
            new Organizer (101, "Globomantics", "Globonantics Technology Corporation"),
            new Venue(201, "Globomatins Main liftion", "lost Street 325", "New York", "USA"),
            LocalDate.of(2024, 1, 10), LocalDate.of(2824, 1, 10)),
        new Event (503, "Carved Fock New Products May",
            new Organizer (102, "Carved Rock", "Carved Rack Sports Equipment"),
            new Venue(202, "Sea View Hotel", "Beach Boulevard 863", "Los Angeles", "USA"),
            LocalDate.of(2024, 2, 29), LocalDate.of(2024, 2, 29)));

    public List<Event> findByOrganizerId(int organizerId) {
        return events.stream().filter(event -> event.organizer().id() == organizerId).toList();
    }
    public Optional<Event> findById(int id) {
        return events.stream ().filter(event -> event.id() == id).findAny();
    }
}
