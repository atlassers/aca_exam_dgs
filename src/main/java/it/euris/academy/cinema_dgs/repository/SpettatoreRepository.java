package it.euris.academy.cinema_dgs.repository;

import it.euris.academy.cinema_dgs.data.model.Spettatore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpettatoreRepository extends JpaRepository<Spettatore, Long> {
}
