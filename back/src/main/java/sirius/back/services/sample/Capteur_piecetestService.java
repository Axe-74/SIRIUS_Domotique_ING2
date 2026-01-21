package sirius.back.services.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.models.sample.Capteur_testpiece;
import sirius.back.repositories.sample.Capteur_testpieceRepository;

import java.util.List;

@Service
public class Capteur_piecetestService {

    @Autowired
    private Capteur_testpieceRepository capteurTestpieceRepository;

//    public Sample findOldestSample(){
//        return sampleRepository.findLastSampleByDate();
//    }
//
//    public Sample findByIdSample(Long idSample) {
//        Optional<Sample> optionalSample = sampleRepository.findById(idSample);
//        return optionalSample.orElse(null);
//    }

    public List<Capteur_testpiece> findAllCapteur_testpiece() {
        return capteurTestpieceRepository.findAll();
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