package it.euris.academy.cinema_dgs.service.impl;

import it.euris.academy.cinema_dgs.data.dto.BigliettoDto;
import it.euris.academy.cinema_dgs.data.model.Biglietto;
import it.euris.academy.cinema_dgs.data.model.Film;
import it.euris.academy.cinema_dgs.data.model.Spettatore;
import it.euris.academy.cinema_dgs.exception.IdDeveEssereNullo;
import it.euris.academy.cinema_dgs.exception.IdNonDeveEssereNullo;
import it.euris.academy.cinema_dgs.repository.BigliettoRepository;
import it.euris.academy.cinema_dgs.service.BigliettoService;
import it.euris.academy.cinema_dgs.service.FilmService;
import it.euris.academy.cinema_dgs.service.SpettatoreService;
import it.euris.academy.cinema_dgs.utils.converter.BigliettoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BigliettoServiceImpl implements BigliettoService {

    @Autowired
    BigliettoRepository bigliettoRepository;

    @Autowired
    BigliettoConverter bigliettoConverter;

    @Autowired
    SpettatoreService spettatoreService;

    @Override
    public List<BigliettoDto> getAll() {
        return bigliettoRepository.findAll().stream().map(bigliettoConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public BigliettoDto get(Long id) {
        Optional<Biglietto> biglietto = bigliettoRepository.findById(id);
        return biglietto.map(bigliettoConverter::fromModelToDto).orElse(null);
    }

    @Override
    public BigliettoDto add(BigliettoDto bigliettoDto) {
        if(bigliettoDto.getId() != null) {
            throw new IdDeveEssereNullo();
        }

        calcolaPrezzo(bigliettoDto);

        return bigliettoConverter.fromModelToDto(bigliettoRepository.save(bigliettoConverter.fromDtoToModel(bigliettoDto)));
    }

    @Override
    public BigliettoDto update(BigliettoDto bigliettoDto) {
        if(bigliettoDto.getId() == null) {
            throw new IdNonDeveEssereNullo();
        }

        return bigliettoConverter.fromModelToDto(bigliettoRepository.save(bigliettoConverter.fromDtoToModel(bigliettoDto)));
    }

    @Override
    public Boolean delete(Long id) {
        bigliettoRepository.deleteById(id);
        return bigliettoRepository.findById(id).isEmpty();
    }

    private void calcolaPrezzo(BigliettoDto bigliettoDto) {
        Biglietto biglietto = bigliettoConverter.fromDtoToModel(bigliettoDto);
        Spettatore spettatore = biglietto.getSpettatore();
        Double sconto = spettatoreService.getSconto(spettatore.getId());
        Film film = biglietto.getSala().getFilm();
        Double prezzoFinale = film.getPrezzoFilm()*sconto;
        biglietto.setPrezzo(prezzoFinale);
    }
}
