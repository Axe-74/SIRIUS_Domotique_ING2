package sirius.back.services;

import sirius.back.models.mesure_v1;
import sirius.back.repositories.mesure_v1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class mesure_v1Service {

    @Autowired
    private mesure_v1Repository mesureRepository;

    public mesure_v1 ajouterMesure(mesure_v1 nouvelleMesure) {
        return mesureRepository.save(nouvelleMesure);
    }

    public List<mesure_v1> findLatestMesure() {
        return mesureRepository.findLatestMesure();
    }

    public mesure_v1 findOldestMesure() {
        return mesureRepository.findOldestMesure();
    }

    public mesure_v1 findLatestDateByCapteur(int idCapteur) {
        return mesureRepository.findLatestDateByCapteur(idCapteur);
    }

    public List<mesure_v1> findFirst1440ByIdCapteurOrderByIdMesureDesc(int  idCapteur) {
        List<mesure_v1> mesures_V1 = mesureRepository.findFirst1440ByIdCapteurOrderByIdMesureDesc(idCapteur);
        mesures_V1.sort(Comparator.comparing(mesure_v1::getIdMesure));
        return mesures_V1;
    }

    public mesure_v1 findLatestMesureByCapteur(int idCapteur) {
        return mesureRepository.findLatestMesureByCapteur(idCapteur);
    }
}
