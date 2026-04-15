package com.pluralsight.springboot.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {

    @Query(value = "SELECT * FROM organizers WHERE id > :cursor ORDER BY id ASC LIMIT :limit", nativeQuery = true)
    List<Organizer> CursorFindById(int cursor, int limit);
}
