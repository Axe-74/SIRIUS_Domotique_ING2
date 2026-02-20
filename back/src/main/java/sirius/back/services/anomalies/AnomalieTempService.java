package sirius.back.services.anomalies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sirius.back.models.Alerte;
import sirius.back.models.ConfigTempAnomalies;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.AlerteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnomalieTempService {

    @Autowired
    private AlerteRepository alerteRepo;

    private static final int FENETRE_MINUTES = 5;
    private static final int SEUIL_WARNING = 3;
    private static final int SEUIL_CRITIQUE = 10;

    private void creerAlerte(Integer idCapteur, String typeAnomalie, String message){
        LocalDateTime dateLimite = LocalDateTime.now().minusMinutes(FENETRE_MINUTES);
        System.err.println("dateLimite: " + dateLimite);

        int erreursRecentes = alerteRepo.countByIdCapteurAndTypeAnomalieAndDateAlerteAfter(idCapteur, typeAnomalie, dateLimite);
        System.err.println("Erreurs : " + erreursRecentes);

        int total = erreursRecentes + 1;
        String niveauAnomalie;

        if (total >= SEUIL_CRITIQUE) {
            niveauAnomalie = "CRITIQUE";
        } else if (total >= SEUIL_WARNING) {
            niveauAnomalie = "WARNING";
        } else {
            niveauAnomalie = "INFO";
        }

        Alerte alerte = new Alerte(LocalDateTime.now(), idCapteur, message, typeAnomalie, niveauAnomalie);
        alerteRepo.save(alerte);

        System.err.println("Alerte de niveau " + niveauAnomalie + " sur le capteur " + idCapteur + " : " + message);
    }

    public boolean verifierSeuils(mesure_v1 mesure, ConfigTempAnomalies config){
        double valeur = mesure.getValeur();
        if (config == null) return true;

        if(valeur < config.getSeuilMinAbsolu()){
            String message = "Anomalie détectée. Température anormalement basse :" + valeur + "°C.";
            System.err.println(message);
            creerAlerte(mesure.getId_capteur(), "SEUIL_MIN", message);
            return false ;
        }

        if(valeur > config.getSeuilMaxAbsolu()){
            String message = "Anomalie détectée. Température anormalement haute :" + valeur + "°C.";
            System.err.println(message);
            creerAlerte(mesure.getId_capteur(), "SEUIL_MAX", message);
            return false;
        }

        else {
            System.out.println("Valeur dans l'intervalle accepté.");
            return true;
        }
    }

    public boolean verifierCoherence(mesure_v1 nouvelleMesure, ConfigTempAnomalies config, List<mesure_v1> historique) {

        if (historique == null || historique.isEmpty()) {
            return true;
        }

        // moyenne glissante
        double somme = 0;
        for (mesure_v1 m : historique) {
            somme += m.getValeur();
        }
        double moyenne = somme / historique.size();

        //ecart
        double nouvelleValeur = nouvelleMesure.getValeur();
        double ecart = Math.abs(nouvelleValeur - moyenne);

        //validation
        if (ecart > config.getSeuilEcartMoyenne()) {
            String message = "Ecart avec les précédentes valeurs supérieur à la valeur maximum autorisée de " + config.getSeuilEcartMoyenne() + ".";
            System.err.println("Incohérence. " + message);
            creerAlerte(nouvelleMesure.getId_capteur(), "SEUIL_ECART", message);
            return false;
        }

        else {
            System.out.println("Valeur cohérente avec les précédentes.");
            return true;
        }
    }

//    public boolean verifierFrequence

}
