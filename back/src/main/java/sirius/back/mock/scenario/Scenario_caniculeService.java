package sirius.back.mock.scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.SimulationConfigTemp;
import sirius.back.repositories.SimulationConfigTempRepository;

import java.util.Random;

@Service
public class Scenario_caniculeService {

    @Autowired
    private SimulationConfigTempRepository configRepository;

    private final Random random = new Random();

    private Long idAncienneConfig = null;

    @Transactional
    public void activerScenario() {
        System.out.println("Lancement du scénario");

        SimulationConfigTemp configActuelle = configRepository.findTopByEtatSimulationTrueOrderByIdSimulationDesc();

        if (configActuelle != null) {
            if (configActuelle.getIdSimulation() != 6) {
                this.idAncienneConfig = configActuelle.getIdSimulation();
                configActuelle.setEtatSimulation(false);
                configRepository.save(configActuelle);
                System.out.println("Configuration" + idAncienneConfig + "mise en pause");
            }
        }
        if (configRepository.existsById(6L)) {
            configRepository.deleteById(6L);
            configRepository.flush();
        }
        SimulationConfigTemp config = new SimulationConfigTemp();
        config.setIdSimulation(6L);
        config.setNomConfig("Scenario Canicule");
        config.setEtatSimulation(true);
        config.setEnvoiBd(true);
        config.setIdCapteurTemp(1);
        config.setIntervalleMinutes(1.0);

        double base = 35.0 + (random.nextDouble() * 5);
        config.setTempCible1(base - 8.0);
        config.setTempCible2(base - 3.0);
        config.setTempCible3(base);
        config.setTempCible4(base - 4.0);
        config.setVariationMaximum(0.1);
        config.setInfluenceHeure(0.1);

        configRepository.save(config);
        System.out.println("Configuration canicule enregistrée");
    }
}