package it.euris.academy.cinema_dgs.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SpettatoreDto {

    private String id;
    private String deleted;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String biglietto;
}
