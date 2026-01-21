package sirius.back.controllers;

import sirius.back.models.Automatisation;
import sirius.back.services.AutomatisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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