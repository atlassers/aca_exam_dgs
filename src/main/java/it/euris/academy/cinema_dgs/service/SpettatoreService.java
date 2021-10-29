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
    Integer etaAsInteger(SpettatoreDto spettatoreDto);
    Boolean isMaggiorenne(SpettatoreDto spettatoreDto);
    Double getSconto(SpettatoreDto spettatoreDto);
}
