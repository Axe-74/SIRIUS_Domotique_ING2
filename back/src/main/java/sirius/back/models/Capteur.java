package sirius.back.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "capteur")
public class Capteur {
    @Id
    @Column(name="idCapteur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCapteur;

    @Column(name = "etatCapteur")
    private Boolean etatCapteur;

    @Column(name = "nomCapteur")
    private String nomCapteur;

    @Column(name = "typeCapteur")
    private String typeCapteur;

    @ManyToOne
    @JoinColumn(name = "id_piece")
    private Piece piece;
}
