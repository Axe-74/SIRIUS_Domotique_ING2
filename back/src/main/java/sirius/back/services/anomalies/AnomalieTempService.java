package sirius.back.services.anomalies;

import org.springframework.stereotype.Service;
import sirius.back.models.ConfigTempAnomalies;
import sirius.back.models.mesure_v1;
import java.util.List;

@Service
public class AnomalieTempService {

    public static boolean verifierSeuils(mesure_v1 mesure, ConfigTempAnomalies config){
        double valeur = mesure.getValeur();
        if (config == null) return true;

        if(valeur < config.getSeuilMinAbsolu()){
            System.err.println("Anomalie détectée. Température anormalement basse :" + valeur + "°C.");
            return false ;
        }

        if(valeur > config.getSeuilMaxAbsolu()){
            System.err.println("Anomalie détectée. Température anormalement haute :" + valeur + "°C.");
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
            System.err.println("Incohérence, écart avec les précédentes valeurs supérieur à la valeur maximum autorisée de " + config.getSeuilEcartMoyenne() + ".");
            return false;
        }

        else {
            System.out.println("Valeur cohérente avec les précédentes.");
            return true;
        }
    }

//    public boolean verifierFrequence

}
