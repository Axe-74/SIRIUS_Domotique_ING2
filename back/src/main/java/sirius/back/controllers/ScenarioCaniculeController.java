package sirius.back.controllers;

import sirius.back.mock.scenario.Scenario_caniculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("ScenarioCanicule")
public class ScenarioCaniculeController {

        @Autowired
        private Scenario_caniculeService scenarioCaniculeService;


        @GetMapping("/lancement")
        public String LancementScénarioCanicule() {
                scenarioCaniculeService.activerScenario();
                return ("Scénario canicule activé");
        }

        @GetMapping("/arret")
        public String ArretScénarioCanicule() {
                scenarioCaniculeService.restaurerAncienneConfig();
                return ("Scénario canicule désactivé");
        }
}


