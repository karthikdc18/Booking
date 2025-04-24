package com.shows.booking.repository;

import com.shows.booking.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Show findByName(String name);

    Optional<List<Show>> findByGenre(String genre);
}