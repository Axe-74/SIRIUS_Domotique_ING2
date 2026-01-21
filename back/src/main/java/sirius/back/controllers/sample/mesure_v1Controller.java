package sirius.back.controllers.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.mesure_v1;
import sirius.back.services.sample.mesure_v1Service;

import java.util.List;

@RestController
@RequestMapping("domotique")
public class mesure_v1Controller {
    @Autowired
    private mesure_v1Service mesureService;

    @PostMapping("ajouter")
    public ResponseEntity<mesure_v1> ajouterMesure(@RequestBody mesure_v1 mesure) {
        mesure_v1 mesureCree = mesureService.ajouterMesure(mesure);
        return new ResponseEntity<>(mesureCree, HttpStatus.CREATED);
    }

    @GetMapping("/one")
    public  ResponseEntity<mesure_v1> findOldestMesure() {
        return new ResponseEntity<>(mesureService.findOldestMesure(), HttpStatus.OK);
    }
}
