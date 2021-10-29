package it.euris.academy.cinema_dgs.service;

import it.euris.academy.cinema_dgs.data.dto.FilmDto;

import java.util.List;

public interface FilmService {

    List<FilmDto> getAll();
    FilmDto get(Long id);
    FilmDto add(FilmDto filmDto);
    FilmDto update(FilmDto filmDto);
    Boolean delete(Long id);
}
