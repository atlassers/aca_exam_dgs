package it.euris.academy.cinema_dgs.service.impl;

import it.euris.academy.cinema_dgs.data.dto.BigliettoDto;
import it.euris.academy.cinema_dgs.data.model.Biglietto;
import it.euris.academy.cinema_dgs.data.model.SalaCinematografica;
import it.euris.academy.cinema_dgs.data.model.Spettatore;
import it.euris.academy.cinema_dgs.repository.BigliettoRepository;
import it.euris.academy.cinema_dgs.service.SpettatoreService;
import it.euris.academy.cinema_dgs.utils.converter.BigliettoConverter;
import it.euris.academy.cinema_dgs.utils.converter.SpettatoreConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BigliettoServiceImplTest {

    @MockBean
    BigliettoRepository bigliettoRepositoryMock;

    @MockBean
    BigliettoConverter bigliettoConverterMock;

    @MockBean
    SpettatoreService spettatoreServiceMock;

    @MockBean
    SpettatoreConverter spettatoreConverterMock;

    //Sut
    @InjectMocks
    BigliettoServiceImpl systemUnderTest;

    @Test
    public void givenRepositoryWhenGetAllThenReturnListOfBigliettoDto() {
        //arrange
        Biglietto bigliettoMock = Biglietto.builder()
                .id(1L)
                .giorno(LocalDate.now())
                .numPosto(42)
                .spettatore(new Spettatore())
                .sala(new SalaCinematografica())
                .prezzo(10.0)
                .build();
        List<Biglietto> bigliettiMock = new ArrayList<>();
        bigliettiMock.add(bigliettoMock);

        Mockito.when(bigliettoRepositoryMock.findAll()).thenReturn(bigliettiMock);

        //act
        List<BigliettoDto> result = systemUnderTest.getAll();

        //assert
        assertEquals(1, result.size());
        verify(bigliettoRepositoryMock, times(1)).findAll();
        verify(bigliettoConverterMock, times(bigliettiMock.size())).fromModelToDto(any(Biglietto.class));
    }
}
