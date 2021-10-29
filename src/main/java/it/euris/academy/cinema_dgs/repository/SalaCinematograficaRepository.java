package it.euris.academy.cinema_dgs.repository;

import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaCinematograficaRepository extends JpaRepository<SalaCinematografica, Long> {
}
