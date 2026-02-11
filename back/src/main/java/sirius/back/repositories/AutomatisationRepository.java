package sirius.back.repositories;


import org.springframework.data.repository.query.Param;
import sirius.back.models.Automatisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutomatisationRepository extends JpaRepository<Automatisation, Integer> {
    @Query(value = "SELECT * FROM Automatisation AS a ORDER BY a.id_automatisation DESC LIMIT 1", nativeQuery = true)
    Automatisation findLastAutomatisationByDate();

    @Query(value = "SELECT * FROM Automatisation AS a ORDER BY a.id_automatisation ",nativeQuery = true)
    List<Automatisation> findAllAutomatisationByDate();

    @Query(value = "UPDATE Autmatissation SET etats = :nouvelEtat WHERE id_automatsation = :id", nativeQuery = true)
    void forcerUpdateEtat(@Param("id") Integer id, @Param("nouvelEtat") Boolean nouvelEtat);

}



