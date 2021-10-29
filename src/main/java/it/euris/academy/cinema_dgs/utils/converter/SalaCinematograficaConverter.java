package it.euris.academy.cinema_dgs.utils.converter;

import it.euris.academy.cinema_dgs.data.dto.SalaCinematograficaDto;
import it.euris.academy.cinema_dgs.data.model.Biglietto;
import it.euris.academy.cinema_dgs.data.model.Cinema;
import it.euris.academy.cinema_dgs.data.model.Film;
import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import it.euris.academy.cinema_dgs.service.BigliettoService;
import it.euris.academy.cinema_dgs.service.CinemaService;
import it.euris.academy.cinema_dgs.service.FilmService;
import it.euris.academy.cinema_dgs.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalaCinematograficaConverter {

    @Autowired
    FilmConverter filmConverter;

    @Autowired
    FilmService filmService;

    @Autowired
    BigliettoConverter bigliettoConverter;

    @Autowired
    BigliettoService bigliettoService;

    @Autowired
    CinemaConverter cinemaConverter;

    @Autowired
    CinemaService cinemaService;

    public SalaCinematograficaDto fromModelToDto(SalaCinematografica salaCinematografica){
        SalaCinematograficaDto salaCinematograficaDto = SalaCinematograficaDto.builder()
                .id(Utilities.fromLongToString(salaCinematografica.getId()))
                .deleted(Utilities.fromBooleanToString(salaCinematografica.getDeleted()))
                .spettatoriMax(Utilities.fromIntegerToString(salaCinematografica.getSpettatoriMax()))
                .cinema(salaCinematografica.getCinema().getId().toString())
                .film(salaCinematografica.getFilm().getId().toString())
                .biglietti(
                        salaCinematografica.getBiglietti().stream().map(Biglietto::getId).map(Object::toString).collect(Collectors.toList())
                )
                .build();

        return salaCinematograficaDto;
    }

    public SalaCinematografica fromDtoToModel(SalaCinematograficaDto salaCinematograficaDto){
        SalaCinematografica salaCinematografica = SalaCinematografica.builder()
                .id(Utilities.fromStringToLong(salaCinematograficaDto.getId()))
                .deleted(Utilities.fromStringToBoolean(salaCinematograficaDto.getDeleted()))
                .spettatoriMax(Utilities.fromStringToInteger(salaCinematograficaDto.getSpettatoriMax()))
                .build();

        if(salaCinematograficaDto.getFilm() != null){
            Film filmDaAggiungere = filmConverter.fromDtoToModel(
                    filmService.get(Long.parseLong(salaCinematograficaDto.getFilm())));
            salaCinematografica.setFilm(filmDaAggiungere);
        }

        if(salaCinematograficaDto.getCinema() != null){
            Cinema cinemaDaAggiungere = cinemaConverter.fromDtoToModel(
                    cinemaService.get(Long.parseLong(salaCinematograficaDto.getCinema())));
            salaCinematografica.setCinema(cinemaDaAggiungere);
        }

        if(salaCinematograficaDto.getBiglietti() != null){
            List<String> idBiglietti = salaCinematograficaDto.getBiglietti();
            List<Biglietto> collect = idBiglietti.stream().map(Utilities::fromStringToLong)
                    .map(b -> bigliettoService.get(b)).map(dto -> bigliettoConverter.fromDtoToModel(dto))
                    .collect(Collectors.toList());
            salaCinematografica.setBiglietti(collect);
        }

        return salaCinematografica;
    }
}
