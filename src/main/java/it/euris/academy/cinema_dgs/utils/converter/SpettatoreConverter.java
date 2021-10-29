package it.euris.academy.cinema_dgs.utils.converter;

import it.euris.academy.cinema_dgs.data.dto.SpettatoreDto;
import it.euris.academy.cinema_dgs.data.model.Biglietto;
import it.euris.academy.cinema_dgs.data.model.Spettatore;
import it.euris.academy.cinema_dgs.service.BigliettoService;
import it.euris.academy.cinema_dgs.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpettatoreConverter {

    @Autowired
    BigliettoService bigliettoService;

    @Autowired
    BigliettoConverter bigliettoConverter;

    public Spettatore fromDtoToModel(SpettatoreDto spettatoreDto){
        Spettatore spettatore = Spettatore.builder()
                .id(Utilities.fromStringToLong(spettatoreDto.getId()))
                .deleted(Utilities.fromStringToBoolean(spettatoreDto.getDeleted()))
                .nome(spettatoreDto.getNome())
                .cognome(spettatoreDto.getCognome())
                .dataNascita(Utilities.fromStringToLocalDate(spettatoreDto.getDataNascita()))
                .build();

        if(spettatoreDto.getBiglietto() != null){
            Biglietto bigliettoDaAggiungere = bigliettoConverter.fromDtoToModel(
                    bigliettoService.get(Long.parseLong(spettatoreDto.getBiglietto())));
            spettatore.setBiglietto(bigliettoDaAggiungere);
        }

        return spettatore;
    }

    public SpettatoreDto fromModelToDto(Spettatore spettatore){
        SpettatoreDto spettatoreDto = SpettatoreDto.builder()
                .id(Utilities.fromLongToString(spettatore.getId()))
                .deleted(Utilities.fromBooleanToString(spettatore.getDeleted()))
                .nome(spettatore.getNome())
                .cognome(spettatore.getCognome())
                .dataNascita(Utilities.fromLocalDateToString(spettatore.getDataNascita()))
                .biglietto(spettatore.getBiglietto().getId().toString())
                .build();

        return spettatoreDto;
    }
}
