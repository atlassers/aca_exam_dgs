package it.euris.academy.cinema_dgs.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BigliettoDto {

    private String id;
    private String deleted;
    private String spettatore;
    private String numPosto;
    private String sala;
    private String prezzo;
}
