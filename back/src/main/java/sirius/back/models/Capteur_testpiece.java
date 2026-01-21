package sirius.back.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "capteur_testpiece")
public class Capteur_testpiece {

    @Id
    @Column(name="id_capteur_testpiece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_capteur_testpiece;

    @Column(name = "nom")
    private String nom;

    @Column(name = "type")
    private String type;

    @Column(name = "etat")
    private String etat;

    @Column(name = "id_piece")
    private Long id_piece;

}
