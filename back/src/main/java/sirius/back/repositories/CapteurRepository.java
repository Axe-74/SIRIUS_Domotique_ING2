package sirius.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.Capteur;

@Repository
public interface CapteurRepository extends JpaRepository<Capteur, Integer> {

}