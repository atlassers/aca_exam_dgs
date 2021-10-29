package it.euris.academy.cinema_dgs.service;

import it.euris.academy.cinema_dgs.data.dto.CinemaDto;

import java.time.LocalDate;
import java.util.List;

public interface CinemaService {
    List<CinemaDto> getAll();
    CinemaDto get(Long id);
    CinemaDto add(CinemaDto cinemaDto);
    CinemaDto update(CinemaDto cinemaDto);
    Boolean delete(Long id);
    
    //metodi specifici
    Double incassoTotalePerGiorno(LocalDate giorno);
    Double incassoTotale();
}
