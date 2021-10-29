package it.euris.academy.cinema_dgs.service.impl;

import it.euris.academy.cinema_dgs.data.dto.BigliettoDto;
import it.euris.academy.cinema_dgs.data.dto.SalaCinematograficaDto;
import it.euris.academy.cinema_dgs.data.dto.SpettatoreDto;
import it.euris.academy.cinema_dgs.data.model.Biglietto;
import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import it.euris.academy.cinema_dgs.data.model.Spettatore;
import it.euris.academy.cinema_dgs.exception.IdDeveEssereNullo;
import it.euris.academy.cinema_dgs.exception.IdNonDeveEssereNullo;
import it.euris.academy.cinema_dgs.repository.SalaCinematograficaRepository;
import it.euris.academy.cinema_dgs.service.BigliettoService;
import it.euris.academy.cinema_dgs.service.SalaCinematograficaService;
import it.euris.academy.cinema_dgs.utils.converter.BigliettoConverter;
import it.euris.academy.cinema_dgs.utils.converter.SalaCinematograficaConverter;
import it.euris.academy.cinema_dgs.utils.converter.SpettatoreConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SalaCinematograficaServiceImpl implements SalaCinematograficaService {

    @Autowired
    SalaCinematograficaRepository salaCinematograficaRepository;

    @Autowired
    SalaCinematograficaConverter salaCinematograficaConverter;

    @Autowired
    BigliettoService bigliettoService;

    @Autowired
    BigliettoConverter bigliettoConverter;

    @Override
    public List<SalaCinematograficaDto> getAll() {
        return salaCinematograficaRepository.findAll().stream().map(salaCinematograficaConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public SalaCinematograficaDto get(Long id) {
        Optional<SalaCinematografica> salaCinematografica = salaCinematograficaRepository.findById(id);
        return salaCinematografica.map(salaCinematograficaConverter::fromModelToDto).orElse(null);
    }

    @Override
    public SalaCinematograficaDto add(SalaCinematograficaDto salaCinematograficaDto) {
        if(salaCinematograficaDto.getId() != null) {
            throw new IdDeveEssereNullo();
        }

        return salaCinematograficaConverter.fromModelToDto(salaCinematograficaRepository.save(salaCinematograficaConverter.fromDtoToModel(salaCinematograficaDto)));
    }

    @Override
    public SalaCinematograficaDto update(SalaCinematograficaDto salaCinematograficaDto) {
        if(salaCinematograficaDto.getId() == null) {
            throw new IdNonDeveEssereNullo();
        }

        return salaCinematograficaConverter.fromModelToDto(salaCinematograficaRepository.save(salaCinematograficaConverter.fromDtoToModel(salaCinematograficaDto)));
    }

    @Override
    public Boolean delete(Long id) {
        salaCinematograficaRepository.deleteById(id);
        return salaCinematograficaRepository.findById(id).isEmpty();
    }

    @Override
    public void svuotaSala(Long id) {
        Optional<SalaCinematografica> sala = salaCinematograficaRepository.findById(id);
        if(sala.isPresent()){
            sala.get().getBiglietti().clear();
        }
    }

    @Override
    public void aggiungiSpettatore(Long id, SpettatoreDto spettatoreDto) {
        Optional<SalaCinematografica> sala = salaCinematograficaRepository.findById(id);
        if(sala.isPresent()){
            SalaCinematografica salaCine = sala.get();
            if(salaCine.getBiglietti().size() < salaCine.getSpettatoriMax()){
                SalaCinematograficaDto salaCineDto = salaCinematograficaConverter.fromModelToDto(salaCine);
                Integer randomNumPosto = new Random().nextInt(100);
                BigliettoDto nuovoBigliettoDto = BigliettoDto.builder()
                        .sala(salaCineDto.getId())
                        .spettatore(spettatoreDto.getId())
                        .numPosto(randomNumPosto.toString())
                        .giorno(LocalDate.now().toString())
                        .build();
                nuovoBigliettoDto = bigliettoService.add(nuovoBigliettoDto);
                Biglietto nuovoBiglietto = bigliettoConverter.fromDtoToModel(nuovoBigliettoDto);
                salaCine.getBiglietti().add(nuovoBiglietto);
            }
        }
    }

    @Override
    public Double incassoSalaPerGiorno(Long id, LocalDate giorno) {
        Optional<SalaCinematografica> sala = salaCinematograficaRepository.findById(id);
        if(sala.isPresent()){
            List<Biglietto> bigliettiGiorno = sala.get().getBiglietti().stream().filter(b -> (b.getGiorno()==giorno))
                    .collect(Collectors.toList());
            Double incasso = 0.0;
            for (Biglietto biglietto:
                 bigliettiGiorno) {
                incasso += biglietto.getPrezzo();
            }
            return incasso;
        }

        return null;
    }

    @Override
    public Double incassoTotaleSala(Long id) {
        Optional<SalaCinematografica> sala = salaCinematograficaRepository.findById(id);
        if(sala.isPresent()){
            List<Biglietto> biglietti = sala.get().getBiglietti();
            Double incasso = 0.0;
            for (Biglietto biglietto:
                    biglietti) {
                incasso += biglietto.getPrezzo();
            }
            return incasso;
        }

        return null;
    }
}
