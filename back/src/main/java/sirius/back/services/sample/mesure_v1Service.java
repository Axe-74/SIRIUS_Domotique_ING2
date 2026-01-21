package sirius.back.services.sample;

import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.mesure_v1;
import sirius.back.repositories.sample.mesure_v1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class mesure_v1Service {

    @Autowired
    private mesure_v1Repository mesureRepository;

    public mesure_v1 ajouterMesure(mesure_v1 nouvelleMesure) {
        return mesureRepository.save(nouvelleMesure);
    }


    public mesure_v1 findOldestMesure() {
        return mesureRepository.findOldestMesure();
    }
}
