package it.euris.academy.cinema_dgs.service.impl;

import it.euris.academy.cinema_dgs.data.dto.CinemaDto;
import it.euris.academy.cinema_dgs.data.model.Cinema;
import it.euris.academy.cinema_dgs.exception.IdDeveEssereNullo;
import it.euris.academy.cinema_dgs.exception.IdNonDeveEssereNullo;
import it.euris.academy.cinema_dgs.repository.CinemaRepository;
import it.euris.academy.cinema_dgs.service.CinemaService;
import it.euris.academy.cinema_dgs.utils.converter.CinemaConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    CinemaConverter cinemaConverter;

    @Override
    public List<CinemaDto> getAll() {
        return cinemaRepository.findAll().stream().map(cinemaConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public CinemaDto get(Long id) {
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        return cinema.map(cinemaConverter::fromModelToDto).orElse(null);
    }

    @Override
    public CinemaDto add(CinemaDto cinemaDto) {
        if(cinemaDto.getId() != null) {
            throw new IdDeveEssereNullo();
        }

        return cinemaConverter.fromModelToDto(cinemaRepository.save(cinemaConverter.fromDtoToModel(cinemaDto)));
    }

    @Override
    public CinemaDto update(CinemaDto cinemaDto) {
        if(cinemaDto.getId() == null) {
            throw new IdNonDeveEssereNullo();
        }

        return cinemaConverter.fromModelToDto(cinemaRepository.save(cinemaConverter.fromDtoToModel(cinemaDto)));
    }

    @Override
    public Boolean delete(Long id) {
        cinemaRepository.deleteById(id);
        return cinemaRepository.findById(id).isEmpty();
    }

    @Override
    public Double incassoTotalePerGiorno(LocalDate giorno) {
        return null;
    }

    @Override
    public Double incassoTotale() {
        return null;
    }
}
