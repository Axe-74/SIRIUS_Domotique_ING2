package sirius.back.services.sample.analyse_sensor; // Vérifie que le package est bon

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.Mesure;
import sirius.back.repositories.sample.MesureRepository;
import sirius.back.services.sample.AutomatisationService;

@Component
public class test_request implements CommandLineRunner {

    @Autowired
    private AutomatisationService automatisationService;
    private MesureRepository mesureRepository;

    @Scheduled(fixedRate = 100, initialDelay = 0)
    public void run(String... args) throws Exception {
        System.out.println("Test SQL");

        try {

            Automatisation result = automatisationService.findOldestAutomatisation();

            if (result != null) {
                System.out.println("Nom trouvé : " + result.getIdAutomatisation());
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            Mesure result = mesureRepository.findLastMesureByDate();

            if (result != null) {
                System.out.println("Mesure trouvée : " + result.getValeur());
            } else {
                System.out.println("Aucun résultat trouvé.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Test SQL");
    }
}