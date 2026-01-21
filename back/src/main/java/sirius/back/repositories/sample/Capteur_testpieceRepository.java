package sirius.back.repositories.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.sample.Capteur_testpiece;

@Repository
public interface Capteur_testpieceRepository extends JpaRepository<Capteur_testpiece, Long> {}
