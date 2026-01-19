package sirius.back.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sirius.back.models.sample.mesure_v1;
import sirius.back.services.sample.mesure_v1Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Component
public class TempSimulationRunner implements CommandLineRunner {

    @Autowired
    private mesure_v1Service mesureService;

    @Override
    public void run(String... args) throws Exception {
        new Thread(this::lancerSimulation).start();
    }

    public void lancerSimulation() {
        double tempActuelle = 20.0;
        double variationMaximum = 0.3;
        int intervalleMinutes = 1;
        double influenceHeure = 0.1;

        Random random = new Random();

        System.out.println("Démarrage du Mock Capteur Temp dans la BD");

        while (true) {
            // 1. Récupération de l'heure
            LocalTime now = LocalTime.now();
            int heure = now.getHour();

            // 2. Définition de la température "cible" par tranche horaire
            double tempCible;
            if (heure >= 0 && heure < 6) { // Nuit
                tempCible = 22.0;
            } else if (heure >= 6 && heure < 12) { // Matin
                tempCible = 28.0;
            } else if (heure >= 12 && heure < 19) { // Aprem
                tempCible = 32.0;
            } else { // Soirée
                tempCible = 26.0;
            }

            // 3. Calcul de la nouvelle température

            double ajustementVersCible = (tempCible - tempActuelle) * influenceHeure;
            double variationAleatoire = (random.nextDouble() * 2 - 1) * variationMaximum;
            tempActuelle = tempActuelle + ajustementVersCible + variationAleatoire;

            // 4. Affichage
            double tempArrondie = Math.round(tempActuelle * 100.0) / 100.0;

            // Enregistrement en BDD
            try {
                mesure_v1 nouvelleMesure = new mesure_v1();
                nouvelleMesure.setValeur((float) tempArrondie);
                nouvelleMesure.setId_capteur(1);
                nouvelleMesure.setDate(LocalDateTime.now());

                mesureService.ajouterMesure(nouvelleMesure);

                System.out.println("[" + LocalTime.now() + "] Sauvegardé en BD : " + tempArrondie + "°C");

            } catch (Exception e) {
                System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
            }

            try {
                long sleepTime = intervalleMinutes * 60 * 10L; //min en ms
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}