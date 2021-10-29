package it.euris.academy.cinema_dgs.service;

import it.euris.academy.cinema_dgs.data.dto.BigliettoDto;

import java.util.List;

public interface BigliettoService {
    List<BigliettoDto> getAll();
    BigliettoDto get(Long id);
    BigliettoDto add(BigliettoDto bigliettoDto);
    BigliettoDto update(BigliettoDto bigliettoDto);
    Boolean delete(Long id);
    
    //metodi specifici
    //void calcolaPrezzo(BigliettoDto bigliettoDto);
}