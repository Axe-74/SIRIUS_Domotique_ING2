package sirius.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.models.Piece;
import sirius.back.repositories.PieceRepository;

import java.util.List;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    public Piece updatePiece(Piece piece){
        return pieceRepository.save(piece);
    }

    public List<Piece> findAllPiece(){
        return pieceRepository.findAll();
    }

}
