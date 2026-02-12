package sirius.back.services.anomalies;

import org.springframework.stereotype.Service;
import sirius.back.models.mesure_v1;

@Service
public class AnomalieTempService {
    public static double seuilMin =  -5.0;
    public static double seuilMax = 40.0;

    public static boolean verifierSeuils(mesure_v1 mesure){
        double valeur = mesure.getValeur();

        if(valeur < seuilMin){
            System.err.println("Anomalie détectée. Température anormalement basse :" + valeur + "°C.");
            return false ;
        }

        if(valeur > seuilMax){
            System.err.println("Anomalie détectée. Température anormalement haute :" + valeur + "°C.");
            return false;
        }

        else {
            System.out.println("Valeur dans l'intervalle accepté.");
            return true;
        }
    }

//    public boolean verifierFrequence

}
