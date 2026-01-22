package sirius.back.services.analyse_sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sirius.back.services.AutomatisationService;

import java.util.concurrent.TimeUnit;

@Component
public class Analyse_Sensor implements CommandLineRunner {

    @Autowired
    private AutomatisationService automatisationService;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            System.out.println("Demérage de l'analyse de température");

            while (true) {
                try {
                    automatisationService.verifierEtMettreAJourAutomatisation();
                    TimeUnit.SECONDS.sleep(5); //temps de refresh de la methode
                } catch (InterruptedException e) {
                    System.err.println("Arrêt de la boucle d'analyse de la température");
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    System.err.println("Erreur dans la boucle d'analyse de température : " + e.getMessage());
                    e.printStackTrace();

                    try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException ex) {}
                }
            }
        }).start();
    }
}