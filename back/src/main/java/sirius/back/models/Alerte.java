package sirius.back.models;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertes")
@Getter
public class Alerte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlerte;

    @Column(name="date_alerte")
    private LocalDateTime dateAlerte;

    @Column(name="id_capteur")
    private Integer idCapteur ;

    @Column(name="message")
    private String message ;

    @Column(name="type_anomalie")
    private String typeAnomalie ;

    @Column(name="niveau_anomalie")
    private String niveauAnomalie ;

    public Alerte() {}

    public Alerte(LocalDateTime dateAlerte, Integer idCapteur, String message, String typeAnomalie, String niveauAnomalie) {
        this.dateAlerte = dateAlerte;
        this.idCapteur = idCapteur;
        this.message = message;
        this.typeAnomalie = typeAnomalie;
        this.niveauAnomalie = niveauAnomalie;
    }
}
