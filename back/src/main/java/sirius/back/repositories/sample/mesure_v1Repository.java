package sirius.back.repositories.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sirius.back.models.sample.mesure_v1;

import java.util.List;

@Repository
public interface mesure_v1Repository extends JpaRepository<mesure_v1, Long> {
    @Query(value = "SELECT DISTINCT ON (id_capteur) * FROM mesure_v1 ORDER BY id_capteur, id_mesure DESC;",nativeQuery = true)
    List<mesure_v1> findLatestMesure();
}
