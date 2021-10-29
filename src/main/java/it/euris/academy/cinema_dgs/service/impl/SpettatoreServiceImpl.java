package it.euris.academy.cinema_dgs.service.impl;

import it.euris.academy.cinema_dgs.data.dto.SpettatoreDto;
import it.euris.academy.cinema_dgs.data.model.Spettatore;
import it.euris.academy.cinema_dgs.exception.IdDeveEssereNullo;
import it.euris.academy.cinema_dgs.exception.IdNonDeveEssereNullo;
import it.euris.academy.cinema_dgs.repository.SpettatoreRepository;
import it.euris.academy.cinema_dgs.service.SpettatoreService;
import it.euris.academy.cinema_dgs.utils.converter.SpettatoreConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpettatoreServiceImpl implements SpettatoreService {

    @Autowired
    SpettatoreRepository spettatoreRepository;

    @Autowired
    SpettatoreConverter spettatoreConverter;

    @Override
    public List<SpettatoreDto> getAll() {
        return spettatoreRepository.findAll().stream().map(spettatoreConverter::fromModelToDto).collect(Collectors.toList());
    }

    @Override
    public SpettatoreDto get(Long id) {
        Optional<Spettatore> spettatore = spettatoreRepository.findById(id);
        return spettatore.map(spettatoreConverter::fromModelToDto).orElse(null);
    }

    @Override
    public SpettatoreDto add(SpettatoreDto spettatoreDto) {
        if(spettatoreDto.getId() != null) {
            throw new IdDeveEssereNullo();
        }

        return spettatoreConverter.fromModelToDto(spettatoreRepository.save(spettatoreConverter.fromDtoToModel(spettatoreDto)));
    }

    @Override
    public SpettatoreDto update(SpettatoreDto spettatoreDto) {
        if(spettatoreDto.getId() == null) {
            throw new IdNonDeveEssereNullo();
        }

        return spettatoreConverter.fromModelToDto(spettatoreRepository.save(spettatoreConverter.fromDtoToModel(spettatoreDto)));
    }

    @Override
    public Boolean delete(Long id) {
        spettatoreRepository.deleteById(id);
        return spettatoreRepository.findById(id).isEmpty();
    }

    @Override
    public Integer etaAsInteger(Long id) {
        Optional<Spettatore> spettatore = spettatoreRepository.findById(id);

        if(spettatore.isPresent()){
            LocalDate dataNascita = spettatore.get().getDataNascita();
            Integer etaAnni = Period.between(dataNascita, LocalDate.now()).getYears();
            return etaAnni;
        }

        return null;
    }

    @Override
    public Boolean isMaggiorenne(Long id) {
        Integer etaAnni = etaAsInteger(id);
        return etaAnni>17;
    }

    @Override
    public Double getSconto(Long id) {
        Integer etaAnni = etaAsInteger(id);
        if(etaAnni<5){
            return 0.5;
        } else if(etaAnni>70){
            return 0.9;
        }
        return 1.0;
    }
}
