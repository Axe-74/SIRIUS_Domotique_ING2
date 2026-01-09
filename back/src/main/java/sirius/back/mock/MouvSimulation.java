package sirius.back.mock;

import java.util.Random;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class MouvSimulation {
    public static void main(String[] args) {
        Random random = new Random();
        boolean dernierEtatMouvement = false;
        double bonusInertie = 0.40;

        System.out.println("Démarrage Mock Capteur Mouv");

        while (true) {
            LocalTime nowTime = LocalTime.now();
            LocalDate nowDate = LocalDate.now();
            int heure = nowTime.getHour();
            DayOfWeek jourSemaine = nowDate.getDayOfWeek();
            boolean isWeekend = (jourSemaine == DayOfWeek.SATURDAY || jourSemaine == DayOfWeek.SUNDAY);

            // 1. Probabilité de BASE (selon l'horaire)
            double chanceDeMouvement = 0.0;
            String periode = "";

            if (isWeekend) {
                if (heure >= 10 && heure < 22) {
                    chanceDeMouvement = 0.30;
                    periode = "Weekend (Journée)";
                } else {
                    chanceDeMouvement = 0.01;
                    periode = "Weekend (Repos)";
                }
            } else {
                if (heure >= 7 && heure < 9) {
                    chanceDeMouvement = 0.30;
                    periode = "Semaine (Matin)";
                } else if (heure >= 19 && heure < 22) {
                    chanceDeMouvement = 0.30;
                    periode = "Semaine (Soir)";
                } else {
                    chanceDeMouvement = 0.01;
                    periode = "Semaine (Calme)";
                }
            }

            // 2. Probabilité si mouvement précédemment : si on a détecté du mouvement la minute précédente, on augmente la proba pour celle-ci
            if (dernierEtatMouvement) {
                chanceDeMouvement = chanceDeMouvement + bonusInertie;
                periode += " + Inertie";
            }

            if (chanceDeMouvement > 1.0) {
                chanceDeMouvement = 1.0;
            }

            // 3. Tirage au sort
            boolean mouvementDetecte = random.nextDouble() < chanceDeMouvement;

            // 4. Mise à jour de la mémoire pour le prochain tour
            dernierEtatMouvement = mouvementDetecte;

            // 5. Affichage
            String message;
            if (mouvementDetecte == true) {
                message = "MOUVEMENT";
            } else {
                message = "Aucun";
            }
            System.out.println("[" + jourSemaine + " " + heure + ":" + nowTime.getMinute() + ":" + nowTime.getSecond() +
                    "] Mode: " + periode + " (Proba: " + String.format("%.2f", chanceDeMouvement) + ") -> " + message);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}