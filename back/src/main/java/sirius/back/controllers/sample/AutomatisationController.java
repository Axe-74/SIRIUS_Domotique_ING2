package sirius.back.controllers.sample;

import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.Sample;
import sirius.back.services.sample.AutomatisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("automatisation")
public class AutomatisationController {

    @Autowired
    private  AutomatisationService automatisationService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Sample> findById(@PathVariable Long id){
//
//        return new ResponseEntity<>(sampleService.findByIdSample(id), HttpStatus.OK);
//    }
//
//    @GetMapping("all")
//    public ResponseEntity<List<Sample>> findAllSample(){
//        return new ResponseEntity<>(sampleService.findAllSample(), HttpStatus.OK);
//    }

    @GetMapping("/Testrequete")
    public  ResponseEntity<Automatisation> findOldestAutomatisation() {
        return new ResponseEntity<>(automatisationService.findOldestAutomatisation(), HttpStatus.OK);
    }

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