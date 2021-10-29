package it.euris.academy.cinema_dgs.controller;

import it.euris.academy.cinema_dgs.data.dto.CinemaDto;
import it.euris.academy.cinema_dgs.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    CinemaService cinemaService;

    @GetMapping("/v1")
    public List<CinemaDto> getAll(){
        return cinemaService.getAll();
    }

    @GetMapping("/v1/{id}")
    public CinemaDto get(@PathVariable("id") Long id){
        return cinemaService.get(id);
    }

    @GetMapping("/v1/incasso/{id}")
    public Double incassoTotale(@PathVariable("id") Long id){
        return cinemaService.incassoTotale(id);
    }

    @GetMapping("/v1/incasso/{id}/{giorno}")
    public Double incassoTotalePerGiorno(@PathVariable("id") Long id, @PathVariable("giorno") LocalDate giorno){
        return cinemaService.incassoTotalePerGiorno(id, giorno);
    }

    @PostMapping("/v1")
    public CinemaDto post(@RequestBody CinemaDto cinemaDto) {
        return cinemaService.add(cinemaDto);
    }

    @PutMapping("/v1")
    public CinemaDto put(@RequestBody CinemaDto cinemaDto) {
        return cinemaService.update(cinemaDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return cinemaService.delete(id);
    }
}
