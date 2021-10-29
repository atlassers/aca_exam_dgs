package it.euris.academy.cinema_dgs.controller;

import it.euris.academy.cinema_dgs.data.dto.SalaCinematograficaDto;
import it.euris.academy.cinema_dgs.service.SalaCinematograficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/salaCinematograficas")
public class SalaCinematograficaController {

    @Autowired
    SalaCinematograficaService salaCinematograficaService;

    @GetMapping("/v1")
    public List<SalaCinematograficaDto> getAll(){
        return salaCinematograficaService.getAll();
    }

    @GetMapping("/v1/{id}")
    public SalaCinematograficaDto get(@PathVariable("id") Long id){
        return salaCinematograficaService.get(id);
    }

    @GetMapping("/v1/incasso/{id}")
    public Double incassoTotale(@PathVariable("id") Long id){
        return salaCinematograficaService.incassoTotaleSala(id);
    }

    @GetMapping("/v1/incasso/{id}/{giorno}")
    public Double incassoTotalePerGiorno(@PathVariable("id") Long id, @PathVariable("giorno") LocalDate giorno){
        return salaCinematograficaService.incassoSalaPerGiorno(id, giorno);
    }

    @PostMapping("/v1")
    public SalaCinematograficaDto post(@RequestBody SalaCinematograficaDto salaCinematograficaDto) {
        return salaCinematograficaService.add(salaCinematograficaDto);
    }

    @PutMapping("/v1")
    public SalaCinematograficaDto put(@RequestBody SalaCinematograficaDto salaCinematograficaDto) {
        return salaCinematograficaService.update(salaCinematograficaDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return salaCinematograficaService.delete(id);
    }
}
