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

//    @GetMapping("/{id}")
//    public ResponseEntity<Sample> findById(@PathVariable Long id){
//
//        return new ResponseEntity<>(sampleService.findByIdSample(id), HttpStatus.OK);
//    }

    @GetMapping("all")
    public ResponseEntity<List<Capteur_testpiece>> findAllPiece(){
        return new ResponseEntity<>(capteurPiecetestService.findAllCapteur_testpiece(), HttpStatus.OK);
    }

//    @GetMapping("dateSample")
//    public ResponseEntity<Sample> findLastSampleByDate() {
//        return new ResponseEntity<>(sampleService.findOldestSample(), HttpStatus.OK);
//    }
//
//    @PostMapping("update")
//    public ResponseEntity<Sample> updateSample(@RequestBody Sample sample){
//        boolean isUpdated = sampleService.updateSampleDate(sample);
//        if(!isUpdated){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(sample, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Long> deleteMapping(@PathVariable Long id){
//        boolean isRemoved = sampleService.deleteSample(id);
//        if(!isRemoved){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return  new ResponseEntity<>(id, HttpStatus.OK);
//    }
}