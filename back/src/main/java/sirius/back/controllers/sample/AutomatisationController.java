package sirius.back.controllers.sample;

import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.Sample;
import sirius.back.services.sample.AutomatisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*" ) // Autorise React
@RestController
@RequestMapping("automatisation")
public class AutomatisationController {

    @Autowired
    private  AutomatisationService automatisationService;


    @GetMapping("/one")
    public  ResponseEntity<Automatisation> findOldestAutomatisation() {
        return new ResponseEntity<>(automatisationService.findOldestAutomatisation(), HttpStatus.OK);
    }


    @GetMapping("/all")
    public  ResponseEntity<List<Automatisation>> findAllAutomatisationByDate() {
        return new ResponseEntity<>(automatisationService.findAllAutomatisationByDate(), HttpStatus.OK);
    }
}