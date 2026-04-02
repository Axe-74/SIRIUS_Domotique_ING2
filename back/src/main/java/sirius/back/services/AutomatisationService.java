package sirius.back.services;

import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.Automatisation;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.AutomatisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.repositories.Parametre_objetRepository;
import sirius.back.models.Parametre_objet;


import java.util.List;
import java.util.Map;

@Service
public class AutomatisationService {

    @Autowired
    private AutomatisationRepository automatisationRepository;
    @Autowired
    private mesure_v1Service mesure_v1Service;
    @Autowired
    private Parametre_objetRepository parametreObjetRepository;


    public Automatisation findOldestAutomatisation() {
        return automatisationRepository.findLastAutomatisationByDate();
    }

    public List<Automatisation> findAllAutomatisationByDate() {
        return automatisationRepository.findAllAutomatisationByDate();
    }

    public List<Automatisation> findAllAutomationWithObjets() {
        return automatisationRepository.findAllAutomationWithObjets();
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
        System.out.println("Température : " + tempActuelle);

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
            } else {
                //System.out.println("Aucun changement pour " + auto.getnom());
            }
        }
    }

    @Transactional
    public void MettreAJourObjetsAutomatisation() {
        List<Automatisation> autoall = automatisationRepository.findAllAutomatisationByDate();
        // On parcourt toutes les automatisations
        for (Automatisation auto : autoall) {
            List<Parametre_objet> objets = auto.getObjetsRelies();
            if (auto.getEtats().equals(Boolean.TRUE)) {
                for (Parametre_objet objet : objets) {
                    objet.setetat(true);
                    parametreObjetRepository.save(objet);
                    //System.out.println(" -> L'objet " + objet.getNom_objet() + " a été activé !");
                }
            } else {
                for (Parametre_objet objet : objets) {
                    objet.setetat(false);
                    parametreObjetRepository.save(objet);
                    //System.out.println(" -> L'objet " + objet.getNom_objet() + " a été désactivé !");
                }
            }
        }
    }
}

