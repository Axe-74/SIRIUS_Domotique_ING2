package sirius.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.ConfigTempAnomalies;

@Repository
public interface ConfigTempAnomaliesRepository extends JpaRepository<ConfigTempAnomalies, Long> {

    ConfigTempAnomalies findTopByEtatActiviteTrueOrderByIdDesc();
}