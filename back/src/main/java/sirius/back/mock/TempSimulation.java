package sirius.back.mock;

import java.util.Random;
import java.time.LocalTime;

public class TempSimulation {
    public static void main(String[] args) {
        double tempActuelle = 20.0;
        double variationMaximum = 0.3;
        int intervalleMinutes = 1;
        double influenceHeure = 0.1;

        Random random = new Random();

        System.out.println("Démarrage Mock Capteur Temp");
        System.out.println("Intervalle : " + intervalleMinutes);

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
            System.out.println("[" + heure + ":" + now.getMinute() + ":" + now.getSecond() + "] Température: " + tempArrondie);

            // 5. Pause
            try {
                long sleepTime = intervalleMinutes * 60 * 1000L; //min en ms
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.out.println("Arrêt du mock");
                break;
            }
        }
    }
}