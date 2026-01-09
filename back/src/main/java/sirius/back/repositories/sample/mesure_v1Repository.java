package sirius.back.repositories.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.sample.mesure_v1;

@Repository
public interface mesure_v1Repository extends JpaRepository<mesure_v1, Long> {
}
