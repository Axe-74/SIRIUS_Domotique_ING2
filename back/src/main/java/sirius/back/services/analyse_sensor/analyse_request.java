package sirius.back.services.analyse_sensor; // Vérifie que le package est bon

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sirius.back.services.AutomatisationService;
import sirius.back.services.mesure_v1Service;


@Component
public class analyse_request implements CommandLineRunner {

    @Autowired
    private AutomatisationService automatisationService;
    @Autowired
    private mesure_v1Service mesure_v1Service;

    @Scheduled(fixedRate = 100, initialDelay = 0)
    public void run(String... args) throws Exception {
    //    System.out.println("Test SQL");


        try{
            automatisationService.verifierEtMettreAJourAutomatisation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //System.out.println("Test SQL");
    }
}