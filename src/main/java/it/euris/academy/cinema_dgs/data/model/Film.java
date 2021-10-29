package it.euris.academy.cinema_dgs.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.euris.academy.cinema_dgs.data.enums.Generi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "film")
@SQLDelete(sql = "UPDATE film SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deleted")
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "film", cascade = CascadeType.REFRESH)
    @Builder.Default
    @JsonIgnore
    private List<SalaCinematografica> sale = new ArrayList<>();

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "autore")
    private String autore;

    @Column(name = "produttore")
    private String produttore;

    @Column(name = "genere")
    @Enumerated(value = EnumType.STRING)
    private Generi genere;

    @Column(name = "eta_minima")
    private Integer etaMinima;

    @Column(name = "durata")
    private Double durata;

    @Column(name = "prezzo_film")
    private Double prezzoFilm;
}
