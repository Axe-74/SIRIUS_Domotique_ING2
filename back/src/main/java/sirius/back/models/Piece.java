package sirius.back.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "piece")
public class Piece {

    @Id
    @Column(name="id_piece")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_piece;

    @Column(name = "nom")
    private String nom;

    @Column(name = "width")
    private Float width;

    @Column(name = "height")
    private Float height;

    @Column(name = "x")
    private Float x;

    @Column(name = "y")
    private Float y;

    @Column(name= "etage")
    private Integer etage;

    @ManyToMany
    @JoinTable(
            name = "piece_parametre_objet",
            joinColumns = @JoinColumn(name = "id_piece"),
            inverseJoinColumns = @JoinColumn(name = "id_objet")
    )
    private List<Parametre_objet> objets;

}
