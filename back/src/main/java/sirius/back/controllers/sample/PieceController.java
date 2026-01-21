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


    @GetMapping("all")
    public ResponseEntity<List<Piece>> findAllPiece(){
        return new ResponseEntity<>(pieceService.findAllPiece(), HttpStatus.OK);
    }

}
