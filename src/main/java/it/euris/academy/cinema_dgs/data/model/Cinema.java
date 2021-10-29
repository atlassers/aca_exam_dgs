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

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cinema")
@SQLDelete(sql = "UPDATE robot SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Cinema {

    List<SalaCinematografica> sale;
}
