package sirius.back.controllers.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirius.back.models.sample.Piece;
import sirius.back.models.sample.Sample;
import sirius.back.services.sample.PieceService;
import sirius.back.services.sample.SampleService;

import java.util.List;

@RestController
@RequestMapping("piece")
public class PieceController {

    @Autowired
    private PieceService pieceService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Sample> findById(@PathVariable Long id){
//
//        return new ResponseEntity<>(sampleService.findByIdSample(id), HttpStatus.OK);
//    }

    @GetMapping("all")
    public ResponseEntity<List<Piece>> findAllPiece(){
        return new ResponseEntity<>(pieceService.findAllPiece(), HttpStatus.OK);
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