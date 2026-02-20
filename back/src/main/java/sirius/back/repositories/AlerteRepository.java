package sirius.back.repositories;

import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sirius.back.models.Alerte;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {
    //pour compter le nombre d'alertes par capteur au cours des x dernières minutes
    int countByIdCapteurAndTypeAnomalieAndDateAlerteAfter(Integer idCapteur, String typeAnomalie, LocalDateTime date);
}
