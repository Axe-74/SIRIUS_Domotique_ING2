package sirius.back.services.sample;

import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.Sample;
import sirius.back.repositories.sample.AutomatisationRepository;
import sirius.back.repositories.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomatisationService {

    @Autowired
    private AutomatisationRepository automatisationRepository;

    public Automatisation findOldestAutomatisation(){
        return automatisationRepository.findLastAutomatisationByDate();
    }


//    public Sample findByIdSample(Long idSample) {
//        Optional<Sample> optionalSample = sampleRepository.findById(idSample);
//        return optionalSample.orElse(null);
//    }
//
//    public List<Sample> findAllSample(){
//        return sampleRepository.findAll();
//    }
//
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