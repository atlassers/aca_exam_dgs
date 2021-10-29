package it.euris.academy.cinema_dgs.controller;
import it.euris.academy.cinema_dgs.data.dto.BigliettoDto;
import it.euris.academy.cinema_dgs.service.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biglietti")
public class BigliettoController {

    @Autowired
    BigliettoService bigliettoService;

    @GetMapping("/v1")
    public List<BigliettoDto> getAll(){
        return bigliettoService.getAll();
    }

    @GetMapping("/v1/{id}")
    public BigliettoDto get(@PathVariable("id") Long id){
        return bigliettoService.get(id);
    }

    @PostMapping("/v1")
    public BigliettoDto post(@RequestBody BigliettoDto bigliettoDto) {
        return bigliettoService.add(bigliettoDto);
    }

    @PutMapping("/v1")
    public BigliettoDto put(@RequestBody BigliettoDto bigliettoDto) {
        return bigliettoService.update(bigliettoDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return bigliettoService.delete(id);
    }
}
