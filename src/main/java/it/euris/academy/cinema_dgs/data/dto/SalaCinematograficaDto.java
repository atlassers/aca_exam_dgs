package it.euris.academy.cinema_dgs.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SalaCinematograficaDto {

    private String id;
    @Builder.Default
    private String deleted="FALSE";
    private String cinema;
    private String spettatoriMax;
    private String film;
    private List<String> biglietti;
}
