package it.euris.academy.cinema_dgs.repository;

import it.euris.academy.cinema_dgs.data.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
