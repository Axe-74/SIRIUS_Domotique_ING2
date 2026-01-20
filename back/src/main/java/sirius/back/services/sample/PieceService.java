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

//    public Sample findOldestSample(){
//        return sampleRepository.findLastSampleByDate();
//    }
//
//    public Sample findByIdSample(Long idSample) {
//        Optional<Sample> optionalSample = sampleRepository.findById(idSample);
//        return optionalSample.orElse(null);
//    }

    public List<Piece> findAllPiece(){
        return pieceRepository.findAll();
    }

//    public boolean updateSampleDate(Sample updatedSample) {
//        Optional<Sample> optionalSample = sampleRepository.findById(updatedSample.getIdSample());
//        if(optionalSample.isPresent()){
//            Sample sample = optionalSample.get();
//            sample.setDateSample(updatedSample.getDateSample());
//            sampleRepository.saveAndFlush(sample);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean deleteSample(Long idSample) {
//        Optional<Sample> optionalSample = sampleRepository.findById(idSample);
//        if (optionalSample.isPresent()) {
//            optionalSample.ifPresent(sample -> sampleRepository.delete(sample));
//            return true;
//        }
//        return false;
//    }
}