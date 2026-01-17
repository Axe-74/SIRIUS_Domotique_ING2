package sirius.back.repositories.sample;


import sirius.back.models.sample.Automatisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AutomatisationRepository extends JpaRepository<Automatisation, Integer> {
    @Query(value = "SELECT * FROM Automatisation AS a ORDER BY a.id_automatisation DESC LIMIT 1", nativeQuery = true)
    Automatisation findLastAutomatisationByDate();
}

