package it.euris.academy.cinema_dgs.service;

import it.euris.academy.cinema_dgs.data.dto.SpettatoreDto;

import java.util.List;

public interface SpettatoreService {

    List<SpettatoreDto> getAll();
    SpettatoreDto get(Long id);
    SpettatoreDto add(SpettatoreDto spettatoreDto);
    SpettatoreDto update(SpettatoreDto spettatoreDto);
    Boolean delete(Long id);

    //metodi specifici
    Boolean isMaggiorenne();
    Integer etaAsInteger();
    Double getSconto(); //TODO sconto -10% se età>70; sconto -50% se età < 5 anni
}
