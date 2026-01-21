package sirius.back.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.models.sample.Piece;
import sirius.back.models.sample.Sample;
import sirius.back.repositories.sample.PieceRepository;
import sirius.back.repositories.sample.SampleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;


    public List<Piece> findAllPiece(){
        return pieceRepository.findAll();
    }

}
