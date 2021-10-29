/**
 * @author Stefano De Giorgi
 * @since 2021-10-29
 */

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
@Table(name = "cinema")
@SQLDelete(sql = "UPDATE cinema SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "deleted")
    @Builder.Default
    private Boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "cinema",cascade = CascadeType.ALL)
    @Builder.Default
    private List<SalaCinematografica> sale = new ArrayList<>();
}
