package it.euris.academy.cinema_dgs.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "biglietto")
@SQLDelete(sql = "UPDATE biglietto SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Biglietto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deleted")
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    @OneToOne(mappedBy = "biglietto", cascade = CascadeType.ALL)
    private Spettatore spettatore;

    @Column(name = "numero_posto")
    private Integer numPosto;

    @ManyToOne (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "sala")
    private SalaCinematografica sala;

    @Column(name = "prezzo")
    private Double prezzo;

    @Column(name = "giorno")
    private LocalDate giorno;
}
