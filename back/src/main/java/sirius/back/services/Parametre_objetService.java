package sirius.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.Automatisation;
import sirius.back.models.Parametre_objet;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.AutomatisationRepository;
import sirius.back.repositories.Parametre_objetRepository;
import sirius.back.repositories.mesure_v1Repository;
import sirius.back.utils.fonctionpure.CalculTemperature;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class Parametre_objetService {

    @Autowired
    private Parametre_objetRepository parametre_objetRepository;
    @Autowired
    private mesure_v1Repository mesure_v1Repository;
    @Autowired
    private AutomatisationRepository automatisationRepository;

    @Transactional
    public void agirSurObjets(double temperatureExterieure, LocalDateTime heure) {

        List<Automatisation> automatisations = automatisationRepository.findAll();
        mesure_v1 mesureInterieure = mesure_v1Repository.findLatestMesureByCapteurOrder(11);
        mesure_v1 mesureMouvement = mesure_v1Repository.findLatestMesureByCapteurOrder(3);

        double tempInterieure = (mesureInterieure != null) ? (double) mesureInterieure.getValeur() : 20.0;
        boolean detectionMouvement = (mesureMouvement != null && mesureMouvement.getValeur() != 0.0);

        int heureActuelle = heure.getHour();
        Set<Parametre_objet> objetsModifies = new HashSet<>();
        Set<Integer> objetsTraitesDansPlage = new HashSet<>();
        for (Automatisation auto : automatisations) {
            if (!auto.getetat()) continue;

            int HeureDebut = auto.getHeureDebut();
            int HeureFin = auto.getHeureFin();
            boolean estDansPlage = (HeureDebut <= HeureFin) ?
                    (heureActuelle >= HeureDebut && heureActuelle < HeureFin) :
                    (heureActuelle >= HeureDebut || heureActuelle < HeureFin);

            if (!estDansPlage) continue;

            double consigne = auto.getValeurConsigne();

            for (Parametre_objet objet : auto.getObjetsRelies()) {

                if (objetsTraitesDansPlage.contains(objet.getId_objet())) continue;

                Map<String, Object> specs = objet.getDonneesJson();
                if (specs == null) specs = new HashMap<>();
                String nom = objet.getNom_objet().toLowerCase();

                //  Radiateur
                if (nom.contains("radiateur")) {
                    double vol = specs.containsKey("volume_piece") ? Double.parseDouble(specs.get("volume_piece").toString()) : 35.0;
                    double coeff = specs.containsKey("coefficient_isolation") ? Double.parseDouble(specs.get("coefficient_isolation").toString()) : 1.2;

                    if (consigne > 0 && tempInterieure < consigne) {
                        double puissance = CalculTemperature.calculerPuissanceRadiateur(tempInterieure, consigne, vol, coeff);
                        objet.setetat(true);
                        specs.put("puissance_watts", puissance);
                        specs.put("temperature", consigne);
                    } else {
                        objet.setetat(false);
                        specs.put("puissance_watts", 0);
                        specs.put("temperature", consigne);
                    }
                }
                // Fenetre
                else if (nom.contains("fenêtre") || nom.contains("fenetre")) {
                    double tempDebutOuverture = specs.containsKey("temp_debut_ouverture") ? Double.parseDouble(specs.get("temp_debut_ouverture").toString()) : 15.0;
                    double tempMaxOuverture = specs.containsKey("temp_max_ouverture") ? Double.parseDouble(specs.get("temp_max_ouverture").toString()) : 30.0;


                    if (consigne > 0 && temperatureExterieure >= tempDebutOuverture && temperatureExterieure <= tempMaxOuverture) {
                        objet.setetat(true);
                        specs.put("degre_ouverture", consigne);
                    } else {
                        objet.setetat(false);
                        specs.put("degre_ouverture", 0);
                    }
                }
                //  Lumiere
                else if (nom.contains("lumiere")) {
                    if (consigne > 0 && detectionMouvement) {
                        objet.setetat(true);
                        specs.put("luminosite", consigne);
                    } else {
                        objet.setetat(false);
                        specs.put("luminosite", 0);
                    }
                }
                // Volet
                else if (nom.contains("volet")) {
                    if (consigne > 0) {
                        objet.setetat(true);
                        specs.put("niveau_ouverture", consigne);
                    } else {
                        objet.setetat(false);
                        specs.put("niveau_ouverture", 0);
                    }
                }

                objet.setDonneesJson(specs);
                objetsModifies.add(objet);
                objetsTraitesDansPlage.add(objet.getId_objet());
            }
        }

        for (Automatisation auto : automatisations) {
            if (!auto.getetat()) continue;

            for (Parametre_objet objet : auto.getObjetsRelies()) {

                if (!objetsTraitesDansPlage.contains(objet.getId_objet())) {

                    Map<String, Object> specs = objet.getDonneesJson();
                    if (specs == null) specs = new HashMap<>();
                    String nom = objet.getNom_objet().toLowerCase();

                    objet.setetat(false);
                    if (nom.contains("radiateur")) specs.put("puissance_watts", 0);
                    else if (nom.contains("fenêtre") || nom.contains("fenetre")) specs.put("degre_ouverture", 0);
                    else if (nom.contains("lumiere")) specs.put("luminosite", 0);
                    else if (nom.contains("volet")) specs.put("niveau_ouverture", 0);

                    objet.setDonneesJson(specs);
                    objetsModifies.add(objet);
                    objetsTraitesDansPlage.add(objet.getId_objet());
                }
            }
        }

        parametre_objetRepository.saveAll(objetsModifies);
    }
}