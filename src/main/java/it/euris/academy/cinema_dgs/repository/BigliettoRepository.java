package it.euris.academy.cinema_dgs.repository;

import it.euris.academy.cinema_dgs.data.model.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigliettoRepository extends JpaRepository<Biglietto, Long> {
}
