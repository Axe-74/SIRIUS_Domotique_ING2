package sirius.back.services.sample.analyse_sensor; // Vérifie que le package est bon

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.mesure_v1;
import sirius.back.services.sample.AutomatisationService;
import sirius.back.services.sample.mesure_v1Service;
import java.util.List;


@Component
public class test_request implements CommandLineRunner {

    @Autowired
    private AutomatisationService automatisationService;
    @Autowired
    private mesure_v1Service mesure_v1Service;

    @Scheduled(fixedRate = 100, initialDelay = 0)
    public void run(String... args) throws Exception {
        System.out.println("Test SQL");


        try{
            automatisationService.verifierEtMettreAJourAutomatisation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        try {
//            // 1. On récupère la liste
//            List<Automatisation> result = automatisationService.findAllAutomatisationByDate();
//
//            // 2. On vérifie si la liste n'est pas vide
//            if (result != null && !result.isEmpty()) {
//                System.out.println("Nombre d'éléments trouvés : " + result.size());
//
//                // 3. LA BOUCLE : Pour chaque "auto" dans "result"
//                for (Automatisation auto : result) {
//                    System.out.println("ID : " + auto.getIdAutomatisation() + " | Nom : " + auto.getnom());
//                }
//            } else {
//                System.out.println("Aucun résultat trouvé.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//
//            mesure_v1 result = mesure_v1Service.findOldestMesure();
//
//            if (result != null) {
//                System.out.println("Mesure trouvée : " + result.getValeur());
//            } else {
//                System.out.println("Aucun résultat trouvé.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("Test SQL");
    }
}