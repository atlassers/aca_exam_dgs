package it.euris.academy.cinema_dgs.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FilmDto {

    private String id;
    private String deleted;
    private String titolo;
    private String autore;
    private String produttore;
    private String genere;
    private String etaMinima;
    private String durata;

    @JsonIgnore
    private List<SalaCinematografica> sale;
}
