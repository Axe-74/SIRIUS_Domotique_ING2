package sirius.back.models.sample;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Autmatissation")
public class Automatisation {

    @Id
    @Column(name="id_automatsation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSample;

    @Column(name = "etats")
    private Boolean dateSample;
}