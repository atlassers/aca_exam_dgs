package it.euris.academy.cinema_dgs.data.model;

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
@Table(name = "sala_cinematografica")
@SQLDelete(sql = "UPDATE sala_cinematografica SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class SalaCinematografica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deleted")
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "cinema")
    private Cinema cinema;

    @Column(name = "spettatori_max")
    private Integer spettatoriMax;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "film")
    private Film film;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Biglietto> biglietti = new ArrayList<>();
}
