package sirius.back.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sirius.back.models.SimulationConfigTemp;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.Parametre_objetRepository;
import sirius.back.repositories.SimulationConfigTempRepository;
import sirius.back.services.Parametre_objetService;
import sirius.back.services.mesure_v1Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
public class TempSimulationAcceleree {

    @Autowired
    private mesure_v1Service mesureService;

    @Autowired
    private SimulationConfigTempRepository configRepository;

    @Autowired
    private Parametre_objetService parametreObjetRepository;

    private Double tempActuelle = null;

    public void lancerSimulation() {
        Random random = new Random();

        try {
            SimulationConfigTemp config = configRepository.findTopByEtatSimulationTrueOrderByIdSimulationDesc();

            if (config == null) {
                System.err.println("Pas de config trouvée");
                return;
            }

            System.out.println("Démarrage de la simulation d'une journée : température extérieure");
            System.out.println(config.getNomConfig());

            // 1. Initialisation de l'heure
            LocalDateTime heureFake = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
            int heure = heureFake.getHour();
            int minute = heureFake.getMinute();
            int seconde = heureFake.getSecond();
            double tempCible;

            int nbMesures = 1440; // 1 mesure toutes les minutes virtuelles, on fait tourner 24h
            long tempsPause = 200; // pour une simulation de 1 minute, 60 000ms/1440 = 42ms environ entre chaque mesure

            if (tempActuelle == null) {
                int idCible = config.getIdCapteurTemp() + 1;
                mesure_v1 lastMesure = mesureService.findLatestMesureByCapteur(idCible);
                if (heure == 0 && minute == 0 && seconde == 0) {
                    tempActuelle = config.getTempCible4();
                } else if (lastMesure != null) {
                    tempActuelle = (double) lastMesure.getValeur();
                } else {
                    tempActuelle = config.getTempCible1();
                }
            }

            for (int i = 0; i < nbMesures; i++) {
                int heureBoucle = heureFake.getHour();

                // 2. Définition de la température "cible" par tranche horaire
                if (heureBoucle >= 0 && heureBoucle < 6) { // Nuit
                    tempCible = config.getTempCible1();
                } else if (heureBoucle >= 6 && heureBoucle < 12) { // Matin
                    tempCible = config.getTempCible2();
                } else if (heureBoucle >= 12 && heureBoucle < 19) { // Aprem
                    tempCible = config.getTempCible3();
                } else { // Soirée
                    tempCible = config.getTempCible4();
                }

                // 3. Calcul de la nouvelle température
                double ajustementVersCible = (tempCible - tempActuelle) * config.getInfluenceHeure();
                double variationAleatoire = (random.nextDouble() * 2 - 1) * config.getVariationMaximum();
                tempActuelle = tempActuelle + ajustementVersCible + variationAleatoire;
                double tempArrondie = Math.round(tempActuelle * 100.0) / 100.0;

                // Enregistrement en BDD
                mesure_v1 nouvelleMesure = new mesure_v1();
                nouvelleMesure.setValeur((float) tempArrondie);
                nouvelleMesure.setIdCapteur(config.getIdCapteurTemp() + 1);
                nouvelleMesure.setDate(heureFake);

                mesureService.ajouterMesure(nouvelleMesure);

                //update de l'heure à chaque fin de boucle
                try {
                    parametreObjetRepository.agirSurObjets(tempArrondie,heureFake);
                }catch (Exception e){}

                heureFake = heureFake.plusMinutes(1);

                try {
                    Thread.sleep(tempsPause);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println("Journée simulée");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
