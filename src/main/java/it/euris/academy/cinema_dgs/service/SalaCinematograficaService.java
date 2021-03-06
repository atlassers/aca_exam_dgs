package it.euris.academy.cinema_dgs.service;

import it.euris.academy.cinema_dgs.data.dto.SalaCinematograficaDto;
import it.euris.academy.cinema_dgs.data.dto.SpettatoreDto;

import java.time.LocalDate;
import java.util.List;

public interface SalaCinematograficaService {

    List<SalaCinematograficaDto> getAll();
    SalaCinematograficaDto get(Long id);
    SalaCinematograficaDto add(SalaCinematograficaDto salaCinematograficaDto);
    SalaCinematograficaDto update(SalaCinematograficaDto salaCinematograficaDto);
    Boolean delete(Long id);
    
    //metodi specifici
    void svuotaSala(Long id);
    void aggiungiSpettatore(Long id, SpettatoreDto spettatoreDto);
    Double incassoSalaPerGiorno(Long id, LocalDate giorno);
    Double incassoTotaleSala(Long id);
}
