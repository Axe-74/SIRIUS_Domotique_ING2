package sirius.back.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirius.back.models.mesure_v1;
import sirius.back.services.mesure_v1Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.time.DayOfWeek;

@Component
public class MouvSimulation {

    @Autowired
    private mesure_v1Service mesureService;

    private final int idCapteurMouv = 3;

    public void lancerSimulation() {
        Random random = new Random();
        boolean dernierEtatMouvement = false;
        double bonusInertie = 0.40;

        System.out.println("Démarrage de la simulation d'une journée : Mouvement");

        int nbMesures = 1440;
        long tempsPause = 200;

        LocalDateTime heureFake = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);

        try {
            for (int i = 0; i < nbMesures; i++) {
                int heure = heureFake.getHour();
                DayOfWeek jourSemaine = heureFake.getDayOfWeek();
                boolean isWeekend = (jourSemaine == DayOfWeek.SATURDAY || jourSemaine == DayOfWeek.SUNDAY);

                // Proba de base selon l'heure
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
                        chanceDeMouvement = 0.40;
                        periode = "Semaine (Matin)";
                    } else if (heure >= 19 && heure < 22) {
                        chanceDeMouvement = 0.40;
                        periode = "Semaine (Soir)";
                    } else {
                        chanceDeMouvement = 0.01;
                        periode = "Semaine (Calme)";
                    }
                }

                // Probabilité si mouvement précédemment
                if (dernierEtatMouvement) {
                    chanceDeMouvement += bonusInertie;
                    periode += " + Inertie";
                }

                if (chanceDeMouvement > 1.0) {
                    chanceDeMouvement = 1.0;
                }

                // Tirage au sort
                boolean mouvementDetecte = random.nextDouble() < chanceDeMouvement;

                dernierEtatMouvement = mouvementDetecte;

                // Enregistrement en BDD
                mesure_v1 nouvelleMesure = new mesure_v1();
                if (mouvementDetecte) {
                    nouvelleMesure.setValeur(1.0f);
                } else {
                    nouvelleMesure.setValeur(0.0f);
                }
                nouvelleMesure.setIdCapteur(idCapteurMouv);
                nouvelleMesure.setDate(heureFake);

                mesureService.ajouterMesure(nouvelleMesure);

                // Incrémentation de l'heure
                heureFake = heureFake.plusMinutes(1);

                try {
                    Thread.sleep(tempsPause);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println("Simulation d'une journée de mouvement finie");

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la simulation de mouvement", e);
        }
    }
}