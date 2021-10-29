package it.euris.academy.cinema_dgs.controller;

import it.euris.academy.cinema_dgs.data.dto.SpettatoreDto;
import it.euris.academy.cinema_dgs.service.SpettatoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spettatori")
public class SpettatoreController {

    @Autowired
    SpettatoreService spettatoreService;

    @GetMapping("/v1")
    public List<SpettatoreDto> getAll(){
        return spettatoreService.getAll();
    }

    @GetMapping("/v1/{id}")
    public SpettatoreDto get(@PathVariable("id") Long id){
        return spettatoreService.get(id);
    }

    @GetMapping("/v1/eta/{id}")
    public Integer etaAsInteger(@RequestBody SpettatoreDto spettatoreDto){
        return spettatoreService.etaAsInteger(spettatoreDto);
    }

    @GetMapping("/v1/eta/maggiorenne/{id}")
    public Boolean isMaggiorenne(@RequestBody SpettatoreDto spettatoreDto){
        return spettatoreService.isMaggiorenne(spettatoreDto);
    }

    @GetMapping("/v1/sconto/{id}")
    public Double getSconto(@RequestBody SpettatoreDto spettatoreDto){
        return spettatoreService.getSconto(spettatoreDto);
    }

    @PostMapping("/v1")
    public SpettatoreDto post(@RequestBody SpettatoreDto spettatoreDto) {
        return spettatoreService.add(spettatoreDto);
    }

    @PutMapping("/v1")
    public SpettatoreDto put(@RequestBody SpettatoreDto spettatoreDto) {
        return spettatoreService.update(spettatoreDto);
    }

    @DeleteMapping("/v1/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return spettatoreService.delete(id);
    }
}
