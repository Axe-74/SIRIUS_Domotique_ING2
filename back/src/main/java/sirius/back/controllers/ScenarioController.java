package sirius.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirius.back.mock.scenario.Scenario_caniculeService;

@RestController
@RequestMapping("/api/scenario")
public class ScenarioController {

    @Autowired
    private Scenario_caniculeService scenarioGenerator;

    @PostMapping("/canicule")
    public String ScenarioCanicule() {
        scenarioGenerator.activerScenario();
        return "Scénario Canicule Aléatoire généré et inséré en BDD ! Le capteur va s'adapter.";
    }
}