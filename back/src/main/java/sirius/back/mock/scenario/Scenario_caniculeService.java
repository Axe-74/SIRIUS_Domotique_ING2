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
    SimulationConfigTempRepository configRepository;

    private final Random random = new Random();

    private Long idAncienneConfig = null;
    private Long idConfigCanicule = null;

    public void lancerScenarioTemporaire(long dureeEnMs) {
        activerScenario();
        new Thread(() -> {
            try {
                System.out.println("Scénario canicule en cours");
                Thread.sleep(dureeEnMs);
                restaurerAncienneConfig();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Transactional
    public void activerScenario() {
        System.out.println("Lancement du scénario");

        SimulationConfigTemp configActuelle = configRepository.findTopByEtatSimulationTrueOrderByIdSimulationDesc();

        if (configActuelle != null) {
            if (configActuelle.getIdSimulation() != 6) {
                idAncienneConfig = configActuelle.getIdSimulation();
                configActuelle.setEtatSimulation(false);
                configRepository.save(configActuelle);
                System.out.println("Configuration" + idAncienneConfig + "mise en pause");
            }
        }

        SimulationConfigTemp config = new SimulationConfigTemp();

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

        SimulationConfigTemp configCanicule = configRepository.save(config);
        idConfigCanicule = configCanicule.getIdSimulation();
        System.out.println("Nouvelle Configuration canicule active avec l'ID" + idConfigCanicule);
    }

    @Transactional
    public void restaurerAncienneConfig() {
        System.out.println("Arrêt du scénario canicule");

        if (idConfigCanicule != null )
            if (configRepository.existsById(idConfigCanicule)) {
                configRepository.deleteById(idConfigCanicule);
                configRepository.flush();
                System.out.println("Configuration Canicule" + idConfigCanicule + "supprimée");
                idConfigCanicule = null;
            }else{
                System.out.println("Aucune configuration canicule");
        } else {
            System.out.println("L'id de la configuration de canicule est null");
        }

        if (idAncienneConfig != null) {
            SimulationConfigTemp ancienneConfig = configRepository.findById(idAncienneConfig).orElse(null);

            if (ancienneConfig != null) {
                ancienneConfig.setEtatSimulation(true);
                configRepository.save(ancienneConfig);
                System.out.println("Ancienne configuration (" + idAncienneConfig + ") restaurée");
            } else {
                System.out.println("Impossible de restaurer l'ancienne configuration");
            }
            idAncienneConfig = null;
        }
    }
}