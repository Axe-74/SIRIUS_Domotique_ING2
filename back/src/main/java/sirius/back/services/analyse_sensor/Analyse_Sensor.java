package sirius.back.services.analyse_sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sirius.back.services.AutomatisationService;
import sirius.back.services.Parametre_objetService;

import java.util.concurrent.TimeUnit;

@Component
public class Analyse_Sensor implements CommandLineRunner {

    @Autowired
    private AutomatisationService automatisationService;

    @Autowired
    private Parametre_objetService parametre_objetService;


    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            System.out.println("Demérage de l'analyse de température");

            while (true) {
                try {
                    automatisationService.verifierEtMettreAJourAutomatisation();
                    automatisationService.MettreAJourObjetsAutomatisation();
                    parametre_objetService.mettreAJourSpecification();
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