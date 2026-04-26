package sirius.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sirius.back.models.Parametre_objet;
import sirius.back.models.Piece;

import java.util.List;

@Repository
public interface Parametre_objetRepository extends JpaRepository<sirius.back.models.Parametre_objet, Long> {
    List<Parametre_objet> findByPieces(Piece piece);

    @Query(value = "SELECT DISTINCT p.* FROM parametre_objet p " +
            "JOIN automatisation_parametre_objet l ON p.id_objet = l.id_objet " +
            "JOIN automatisation a ON l.id_automatisation = a.id_automatisation " +
            "WHERE a.etat = true", nativeQuery = true)
    List<Parametre_objet> findObjetsAvecAutomatisationActive();
}

