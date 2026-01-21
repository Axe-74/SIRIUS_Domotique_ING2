package sirius.back.controllers.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sirius.back.models.sample.Capteur_testpiece;
import sirius.back.services.sample.Capteur_piecetestService;

import java.util.List;

@RestController
@RequestMapping("capteur_testpiece")
public class Capteur_testpieceController {

    @Autowired
    private Capteur_piecetestService capteurPiecetestService;

    
    @GetMapping("all")
    public ResponseEntity<List<Capteur_testpiece>> findAllPiece(){
        return new ResponseEntity<>(capteurPiecetestService.findAllCapteur_testpiece(), HttpStatus.OK);
    }
    
}
