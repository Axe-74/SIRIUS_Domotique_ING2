package sirius.back.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sirius.back.models.SimulationConfigTemp;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.SimulationConfigTempRepository;
import sirius.back.services.mesure_v1Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Component
public class TempSimulationRunner implements CommandLineRunner {

    @Autowired
    private mesure_v1Service mesureService;

    @Autowired
    private SimulationConfigTempRepository configRepository;

    private Double tempActuelle = null;

    @Override
    public void run(String... args) throws Exception {
        new Thread(this::lancerSimulation).start();
    }

    public void lancerSimulation() {
        Random random = new Random();

        while (true) {
            try {
                SimulationConfigTemp config = configRepository.findTopByEtatSimulationTrueOrderByIdSimulationDesc();

                if (config == null) {
                    System.out.println("⚠️ Aucune config active trouvée. Pause...");
                    Thread.sleep(5000);
                    continue;
                }

                if (tempActuelle == null) {
                    mesure_v1 lastMesure = mesureService.findOldestMesure();
                    if (lastMesure != null) {
                        tempActuelle = (double) lastMesure.getValeur();
                    } else {
                        tempActuelle = 20.0;
                    }
                }

        System.out.println("Démarrage du Mock Capteur Temp dans la BD");
        System.out.println(config.getNomConfig());

            // 1. Récupération de l'heure
            LocalTime now = LocalTime.now();
            int heure = now.getHour();
            double tempCible;

            // 2. Définition de la température "cible" par tranche horaire
            if (heure >= 0 && heure < 6) { // Nuit
                tempCible = config.getTempCible1();
            } else if (heure >= 6 && heure < 12) { // Matin
                tempCible = config.getTempCible2();
            } else if (heure >= 12 && heure < 19) { // Aprem
                tempCible = config.getTempCible3();
            } else { // Soirée
                tempCible = config.getTempCible4();
            }

            // 3. Calcul de la nouvelle température

            double ajustementVersCible = (tempCible - tempActuelle) * config.getInfluenceHeure();
            double variationAleatoire = (random.nextDouble() * 2 - 1) * config.getVariationMaximum();
            tempActuelle = tempActuelle + ajustementVersCible + variationAleatoire;

            // 4. Affichage
            double tempArrondie = Math.round(tempActuelle * 100.0) / 100.0;

            // Enregistrement en BDD
            if (config.getEnvoiBd()) {
                mesure_v1 nouvelleMesure = new mesure_v1();
                nouvelleMesure.setValeur((float) tempArrondie);
                nouvelleMesure.setId_capteur(config.getIdCapteurTemp());
                nouvelleMesure.setDate(LocalDateTime.now());

                mesureService.ajouterMesure(nouvelleMesure);

                System.out.println("[" + LocalTime.now() + "] Sauvegardé en BD : " + tempArrondie + "°C");

            } else {
                System.out.println("Pas sauvegardé en BD");
            }

            try {
                long sleepTime = Math.round(config.getIntervalleMinutes() * 60 * 1000); //min en ms
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                break;
            } catch (Exception e) {
                System.err.println("Erreur Mock : " + e.getMessage());
                try { Thread.sleep(5000); } catch (InterruptedException ex) {}
            }
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}