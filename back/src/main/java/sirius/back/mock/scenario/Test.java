package sirius.back.mock.scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Test implements CommandLineRunner {

    @Autowired
    private Scenario_caniculeService scenarioService;

    @Override
    public void run(String... args) throws Exception {
        try {
            scenarioService.lancerScenarioTemporaire(10000);
        } catch (Exception e) {
            System.err.println("Erreur dans le lancement du scénario:" + e.getMessage());
        }
    }
}