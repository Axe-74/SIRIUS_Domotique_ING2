package sirius.back.mock;

import java.util.Random;

public class TempSimulation {
    public static void main(String[] args) {
        double tempActuelle = 20.0;
        double variationMaximum = 0.5;
        int intervalleMinutes = 1;

        Random random = new Random();

        System.out.println("Démarrage Mock Capteur Temp");
        System.out.println("Intervalle : " + intervalleMinutes);

        while (true) {

            System.out.println(tempActuelle);

            double variation = (random.nextDouble() * 2 - 1) * variationMaximum;

            tempActuelle = tempActuelle + variation;

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
