package sirius.back.services.sample;

import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.mesure_v1;
import sirius.back.repositories.sample.AutomatisationRepository;
import sirius.back.repositories.sample.mesure_v1Repository;
import sirius.back.repositories.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.services.sample.mesure_v1Service;
import java.util.List;

@Service
public class AutomatisationService {

    @Autowired
    private AutomatisationRepository automatisationRepository;
    @Autowired
    private mesure_v1Service mesure_v1Service;

    public Automatisation findOldestAutomatisation() {
        return automatisationRepository.findLastAutomatisationByDate();
    }

    public List<Automatisation> findAllAutomatisationByDate() {
        return automatisationRepository.findAllAutomatisationByDate();
    }


    @Transactional
    public void verifierEtMettreAJourAutomatisation() {
        List<Automatisation> autoall = automatisationRepository.findAllAutomatisationByDate();
        mesure_v1 dernierReleve = mesure_v1Service.findOldestMesure();

        // Sécurité si aucune automatisation ou valeur en base
        if (dernierReleve == null || autoall == null) {
            System.out.println("Données manquantes (pas d'automatisation ou pas de relevé).");
            return;
        }

        double tempActuelle = dernierReleve.getValeur();
        //System.out.println("Température : " + tempActuelle);

        // On parcourt toutes les automatisations
        for (Automatisation auto : autoall) {
            Double seuilDeDeclenchement = auto.getSeuilDeDeclenchement();
            //System.out.println("Seuil : " + seuilDeDeclenchement);

            // Cas température en dessous du seuil de déclenchement
            if (tempActuelle <= seuilDeDeclenchement) {
                if (!Boolean.TRUE.equals(auto.getEtats())) {
                    //System.out.println("Température basse, activation de " + auto.getnom());
                    auto.setEtats(true);
                    automatisationRepository.saveAndFlush(auto);
                } else {
                    //System.out.println("Température basse, " + auto.getnom() + " déja activée");
                }
            }

            // Cas température au-dessus du seuil de déclenchement
            else if (tempActuelle > seuilDeDeclenchement) {
                if (Boolean.TRUE.equals(auto.getEtats())) {
                    //System.out.println("Température a un seuil normale: Désactivation de " + auto.getnom());
                    auto.setEtats(false);
                    automatisationRepository.saveAndFlush(auto);
                } else {
                    //System.out.println("Température normale mais " + auto.getnom() + " déja désactivé");
                }
            }
            else {
                //System.out.println("Aucun changement pour " + auto.getnom());
            }
        }
    }
}
