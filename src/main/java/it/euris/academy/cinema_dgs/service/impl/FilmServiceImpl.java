package it.euris.academy.cinema_dgs.service.impl;

import it.euris.academy.cinema_dgs.data.dto.FilmDto;
import it.euris.academy.cinema_dgs.data.model.Film;
import it.euris.academy.cinema_dgs.exception.IdDeveEssereNullo;
import it.euris.academy.cinema_dgs.exception.IdNonDeveEssereNullo;
import it.euris.academy.cinema_dgs.repository.FilmRepository;
import it.euris.academy.cinema_dgs.service.FilmService;
import it.euris.academy.cinema_dgs.utils.converter.FilmConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    FilmConverter filmConverter;

    @Override
    public List<FilmDto> getAll() {
        return filmRepository.findAll().stream().map(filmConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public FilmDto get(Long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.map(filmConverter::fromModelToDto).orElse(null);
    }

    @Override
    public FilmDto add(FilmDto filmDto) {
        if(filmDto.getId() != null) {
            throw new IdDeveEssereNullo();
        }

        return filmConverter.fromModelToDto(filmRepository.save(filmConverter.fromDtoToModel(filmDto)));
    }

    @Override
    public FilmDto update(FilmDto filmDto) {
        if(filmDto.getId() == null) {
            throw new IdNonDeveEssereNullo();
        }

        return filmConverter.fromModelToDto(filmRepository.save(filmConverter.fromDtoToModel(filmDto)));
    }

    @Override
    public Boolean delete(Long id) {
        filmRepository.deleteById(id);
        return filmRepository.findById(id).isEmpty();
    }
}
