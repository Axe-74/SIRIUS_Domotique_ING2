package sirius.back.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.models.sample.Capteur_testpiece;
import sirius.back.repositories.sample.Capteur_testpieceRepository;

import java.util.List;

@Service
public class Capteur_piecetestService {

    @Autowired
    private Capteur_testpieceRepository capteurTestpieceRepository;


    public List<Capteur_testpiece> findAllCapteur_testpiece() {
        return capteurTestpieceRepository.findAll();
    }

}
