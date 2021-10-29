package it.euris.academy.cinema_dgs.utils.converter;

import it.euris.academy.cinema_dgs.data.dto.CinemaDto;
import it.euris.academy.cinema_dgs.data.model.Cinema;
import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import it.euris.academy.cinema_dgs.service.SalaCinematograficaService;
import it.euris.academy.cinema_dgs.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaConverter {

    @Autowired
    SalaCinematograficaService salaCinematograficaService;

    @Autowired
    SalaCinematograficaConverter salaCinematograficaConverter;

    public Cinema fromDtoToModel(CinemaDto cinemaDto){
        Cinema cinema = Cinema.builder()
                .id(Utilities.fromStringToLong(cinemaDto.getId()))
                .deleted(Utilities.fromStringToBoolean(cinemaDto.getDeleted()))
                .build();

        if(cinemaDto.getSale() != null){
            List<String> saleNelDto = cinemaDto.getSale();
            List<SalaCinematografica> saleModel = new ArrayList<>();
            for (String idSala:
                 saleNelDto) {
                Long id = Utilities.fromStringToLong(idSala);
                SalaCinematografica salaDaAggiungere = salaCinematograficaConverter.fromDtoToModel(salaCinematograficaService.get(id));
                saleModel.add(salaDaAggiungere);
            }
            cinema.setSale(saleModel);
        }

        return cinema;
    }

    public CinemaDto fromModelToDto(Cinema cinema){
        CinemaDto cinemaDto = CinemaDto.builder()
                .id(Utilities.fromLongToString(cinema.getId()))
                .deleted(Utilities.fromBooleanToString(cinema.getDeleted()))
                .sale(cinema.getSale().stream().map(SalaCinematografica::getId).map(Object::toString).collect(Collectors.toList()))
                .build();

        return cinemaDto;
    }
}
