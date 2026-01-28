package com.daniels.tickets.events;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VenueRepository extends JpaRepository<Venue,Integer> {

    
}

/*
@Repository
public class VenueRepository {

    private final List<Venue> venues = List.of(
        new Venue(201, "Globomantics Main Office", "Test Street 325", "New York", "USA"),
        new Venue(202, "Sea View Hotel", "Beach Boulevard 863", "LA", "USA")
    );
    
    public Optional<Venue> findById(int id){
        return venues.stream().filter(venue -> venue.id() == id).findAny();
    }
    
}*/
