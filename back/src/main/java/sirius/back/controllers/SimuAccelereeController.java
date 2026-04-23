package sirius.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirius.back.mock.MouvSimulation;
import sirius.back.mock.TempInterieureSimulation;
import sirius.back.mock.TempSimulationAcceleree;

@RestController
@RequestMapping("simulation_acceleree")
@CrossOrigin(origins = "*")
public class SimuAccelereeController {

    @Autowired
    private TempSimulationAcceleree tempSimulationAcceleree;

    @Autowired
    private TempInterieureSimulation tempInterieureSimulation;

    @Autowired
    private MouvSimulation mouvSimulation;

    @PostMapping("journee")
    public ResponseEntity<String> ajouterTempSimulationAcceleree() {

        new Thread(() -> {
            tempSimulationAcceleree.lancerSimulation();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tempInterieureSimulation.lancerSimulation();
        }).start();

        new Thread(() -> {
            mouvSimulation.lancerSimulation();
        }).start();

        return new ResponseEntity<>("Simulation d'une journée en 2 minutes lancée", HttpStatus.CREATED);
    }
}

