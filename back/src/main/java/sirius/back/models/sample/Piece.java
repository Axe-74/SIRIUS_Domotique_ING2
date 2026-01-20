package sirius.back.models.sample;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
}
