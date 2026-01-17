package sirius.back.services.sample;

import sirius.back.models.sample.Automatisation;
import sirius.back.models.sample.Mesure;
import sirius.back.models.sample.Sample;
import sirius.back.repositories.sample.AutomatisationRepository;
import sirius.back.repositories.sample.MesureRepository;
import sirius.back.repositories.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomatisationService {

    @Autowired
    private AutomatisationRepository automatisationRepository;

//    @Autowired
//    private MesureRepository mesureRepository;

    public Automatisation findOldestAutomatisation(){
        return automatisationRepository.findLastAutomatisationByDate();
    }


//    public void verifierEtMettreAJourAutomatisation() {
//        // 1. Récupérer l'automatisation cible (celle que tu récupérais déjà)
//        List<Automatisation> auto = automatisationRepository.findAllAutomatisationByDate();
//
//        // 2. Récupérer la dernière température connue
//        Mesure dernierReleve = mesureRepository.findLastMesureByDate();
//
//        if (auto != null && dernierReleve != null) {
//            System.out.println("Température actuelle : " + dernierReleve.getValeur());
//            System.out.println("État actuel de l'automatisation : " + auto.getFirst());
//
//            // 3. LA LOGIQUE : Si temp < 20, on active
//            if (dernierReleve.getValeur() < 20.0) {
//                if (!auto.getEtats()) { // Petite optimisation : on update seulement si c'était éteint
//                    System.out.println("ALERTE : Température basse ! Activation de l'automatisation.");
//
//                    auto.setEtats(true); // On change l'état en mémoire
//
//                    // 4. L'UPDATE SQL se fait ici automatiquement
//                    automatisationRepository.save(auto);
//                } else {
//                    System.out.println("Température basse, mais l'automatisation est déjà active.");
//                }
//            } else {
//                // (Optionnel) Sinon on désactive ?
//                if (auto.getEtats()) {
//                    System.out.println("Température OK. Désactivation.");
//                    auto.setEtats(false);
//                    automatisationRepository.save(auto);
//                }
//            }
//        } else {
//            System.out.println("Données manquantes (pas d'automatisation ou pas de relevé).");
//        }
//    }



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