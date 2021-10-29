package it.euris.academy.cinema_dgs.utils.converter;

import it.euris.academy.cinema_dgs.data.dto.FilmDto;
import it.euris.academy.cinema_dgs.data.model.Film;
import it.euris.academy.cinema_dgs.utils.Utilities;
import org.springframework.stereotype.Component;

@Component
public class FilmConverter {

    public FilmDto fromModelToDto(Film film){
        FilmDto filmDto = FilmDto.builder()
                .id(Utilities.fromLongToString(film.getId()))
                .deleted(Utilities.fromBooleanToString(film.getDeleted()))
                .titolo(film.getTitolo())
                .autore(film.getAutore())
                .produttore(film.getProduttore())
                .genere(Utilities.fromGeneriToString(film.getGenere()))
                .etaMinima(Utilities.fromIntegerToString(film.getEtaMinima()))
                .durata(Utilities.fromDoubleToString(film.getDurata()))
                .prezzoFilm(Utilities.fromDoubleToString(film.getPrezzoFilm()))
                .build();

        return filmDto;
    }

    public Film fromDtoToModel(FilmDto filmDto){
        Film film = Film.builder()
                .id(Utilities.fromStringToLong(filmDto.getId()))
                .deleted(Utilities.fromStringToBoolean(filmDto.getDeleted()))
                .titolo(filmDto.getTitolo())
                .autore(filmDto.getAutore())
                .produttore(filmDto.getProduttore())
                .genere(Utilities.fromStringToGeneri(filmDto.getGenere()))
                .etaMinima(Utilities.fromStringToInteger(filmDto.getEtaMinima()))
                .durata(Utilities.fromStringToDouble(filmDto.getDurata()))
                .prezzoFilm(Utilities.fromStringToDouble(filmDto.getPrezzoFilm()))
                .build();

        return film;
    }
}
