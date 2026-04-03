package sirius.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.Parametre_objet;
import sirius.back.models.Piece;

import java.util.List;

@Repository
public interface Parametre_objetRepository extends JpaRepository<sirius.back.models.Parametre_objet, Long> {
    List<Parametre_objet> findByPieces(Piece piece);
}

