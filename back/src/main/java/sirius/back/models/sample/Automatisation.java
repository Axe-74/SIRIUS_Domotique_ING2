package sirius.back.models.sample;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Autmatisation")
public class Automatisation {

    @Id
    @Column(name="id_automatisation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutomatisation;

    @Column(name = "etat")
    private Boolean etats;

    @Column(name = "nom")
    private String nom;
}