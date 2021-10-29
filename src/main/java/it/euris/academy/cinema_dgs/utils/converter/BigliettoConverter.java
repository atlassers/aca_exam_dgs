package it.euris.academy.cinema_dgs.utils.converter;

import it.euris.academy.cinema_dgs.data.dto.BigliettoDto;
import it.euris.academy.cinema_dgs.data.model.Biglietto;
import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import it.euris.academy.cinema_dgs.data.model.Spettatore;
import it.euris.academy.cinema_dgs.service.SalaCinematograficaService;
import it.euris.academy.cinema_dgs.service.SpettatoreService;
import it.euris.academy.cinema_dgs.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BigliettoConverter {

    @Autowired
    SpettatoreConverter spettatoreConverter;

    @Autowired
    SpettatoreService spettatoreService;

    @Autowired
    SalaCinematograficaConverter salaCinematograficaConverter;

    @Autowired
    SalaCinematograficaService salaCinematograficaService;

    public Biglietto fromDtoToModel(BigliettoDto bigliettoDto){
        Biglietto biglietto = Biglietto.builder()
                .id(Utilities.fromStringToLong(bigliettoDto.getId()))
                .deleted(Utilities.fromStringToBoolean(bigliettoDto.getDeleted()))
                .numPosto(Utilities.fromStringToInteger(bigliettoDto.getNumPosto()))
                .prezzo(Utilities.fromStringToDouble(bigliettoDto.getPrezzo()))
                .giorno(Utilities.fromStringToLocalDate(bigliettoDto.getGiorno()))
                .build();

        if(bigliettoDto.getSpettatore() != null){
            Long idSpettatore = Utilities.fromStringToLong(bigliettoDto.getSpettatore());
            Spettatore spettatoreDaAggiungere = spettatoreConverter.fromDtoToModel(spettatoreService.get(idSpettatore));
            biglietto.setSpettatore(spettatoreDaAggiungere);
        }

        if(bigliettoDto.getSala() != null){
            Long idSala = Utilities.fromStringToLong(bigliettoDto.getSala());
            SalaCinematografica salaDaAggiungere = salaCinematograficaConverter.fromDtoToModel(salaCinematograficaService.get(idSala));
            biglietto.setSala(salaDaAggiungere);
        }

        return biglietto;
    }

    public BigliettoDto fromModelToDto(Biglietto biglietto){
        BigliettoDto bigliettoDto = BigliettoDto.builder()
                .id(Utilities.fromLongToString(biglietto.getId()))
                .deleted(Utilities.fromBooleanToString(biglietto.getDeleted()))
                .spettatore(biglietto.getSpettatore().getId().toString())
                .numPosto(Utilities.fromIntegerToString(biglietto.getNumPosto()))
                .sala(biglietto.getSala().getId().toString())
                .prezzo(Utilities.fromDoubleToString(biglietto.getPrezzo()))
                .giorno(Utilities.fromLocalDateToString(biglietto.getGiorno()))
                .build();

        return bigliettoDto;
    }
}
