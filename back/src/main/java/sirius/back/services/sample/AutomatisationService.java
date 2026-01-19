package sirius.back.services.sample;

import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.mesure_v1;
import sirius.back.models.sample.Sample;
import sirius.back.repositories.sample.AutomatisationRepository;
import sirius.back.repositories.sample.mesure_v1Repository;
import sirius.back.repositories.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.services.sample.mesure_v1Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutomatisationService {

    @Autowired
    private AutomatisationRepository automatisationRepository;
    @Autowired
    private mesure_v1Service mesure_v1Service;

//    @Autowired
//    private MesureRepository mesureRepository;

    public Automatisation findOldestAutomatisation() {
        return automatisationRepository.findLastAutomatisationByDate();
    }

    public List<Automatisation> findAllAutomatisationByDate() {
        return automatisationRepository.findAllAutomatisationByDate();
    }


    @Transactional
    public void verifierEtMettreAJourAutomatisation() {
        List<Automatisation> autoall = automatisationRepository.findAllAutomatisationByDate();

        mesure_v1 dernierReleve = mesure_v1Service.findOldestMesure();
        for (Automatisation auto : autoall) {
            if (auto != null && dernierReleve != null) {
                System.out.println("Température actuelle : " + dernierReleve.getValeur());
                System.out.println("État actuel de l'automatisation : " + auto.getetat());

                if (dernierReleve.getValeur() <= 19) {
                    if (auto.getEtats() == false) {
                        System.out.println("ALERTE : Température basse ! Activation de l'automatisation.");
                        // update de l'automatisation en base
                        automatisationRepository.forcerUpdateEtat(auto.getIdAutomatisation(), false);
                        auto.setEtats(true);

                        automatisationRepository.save(auto);
                    } else {
                        System.out.println("Température basse, mais l'automatisation est déjà active.");
                    }
                if( dernierReleve.getValeur() > 19 ) {}
                    if( dernierReleve.getValeur() <= 21 ) {}
                    System.out.println("aucun changement pour l'automatisation : " + auto.getnom());
                    } else {
                        if (auto.getEtats() == true ) {
                            System.out.println("Température OK. Désactivation.");
                            auto.setEtats(false);
                            automatisationRepository.save(auto);
                        }
                    }
            } else {
                System.out.println("Données manquantes (pas d'automatisation ou pas de relevé).");
            }
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
}