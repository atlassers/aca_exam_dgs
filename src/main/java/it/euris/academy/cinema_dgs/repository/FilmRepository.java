package it.euris.academy.cinema_dgs.repository;

import it.euris.academy.cinema_dgs.data.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
