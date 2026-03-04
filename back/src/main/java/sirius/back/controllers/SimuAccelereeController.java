package sirius.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirius.back.mock.TempSimulationAcceleree;

@RestController
@RequestMapping("simulation_acceleree")
@CrossOrigin(origins = "*")
public class SimuAccelereeController {

    @Autowired
    private TempSimulationAcceleree tempSimulationAcceleree;

    @PostMapping("journee")
    public ResponseEntity<String> ajouterTempSimulationAcceleree() {

        new Thread(() -> {
            tempSimulationAcceleree.lancerSimulation();
        }).start();

        return new ResponseEntity<>("Simulation d'une journée en 2 minutes lancée", HttpStatus.CREATED);
    }
}

