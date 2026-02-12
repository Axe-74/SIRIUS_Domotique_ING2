package sirius.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sirius.back.models.Piece;
import sirius.back.services.PieceService;

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

    @PostMapping("update")
    public ResponseEntity<Piece> updatePiece(@RequestBody Piece piece) {
        return new ResponseEntity<>(pieceService.updatePiece(piece), HttpStatus.OK);
    }

}
