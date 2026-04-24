package sirius.back.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "piece")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id_piece")
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

    @ToString.Exclude // pour prevent une boucle infinie
    @ManyToMany
    @JoinTable(
            name = "piece_parametre_objet",
            joinColumns = @JoinColumn(name = "id_piece"),
            inverseJoinColumns = @JoinColumn(name = "id_objet")
    )
    private List<Parametre_objet> objets;

}
