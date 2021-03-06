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
public class CinemaDto {

    private String id;
    @Builder.Default
    private String deleted="FALSE";
    private List<String> sale;
}
