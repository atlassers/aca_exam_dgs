package it.euris.academy.cinema_dgs.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
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
    @NotNull
    private String titolo;
    @NotNull
    private String autore;
    @NotNull
    private String produttore;
    @NotNull
    private String genere;
    @NotNull
    private String etaMinima;
    @NotNull
    private String durata;
    @NotNull
    private String prezzoFilm;

    @JsonIgnore
    private List<SalaCinematografica> sale;
}
