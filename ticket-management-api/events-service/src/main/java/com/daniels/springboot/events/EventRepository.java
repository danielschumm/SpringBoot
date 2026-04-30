package com.daniels.springboot.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByOrganizerId(int organizerId);

    @Query(value = "SELECT * FROM events WHERE organizer_id = :organizerId ORDER BY id ASC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Event> pagedFindByOrganizerId(int organizerId, int offset, int limit);
}
