package sirius.back.utils.fonctionpure;

public class CalculTemperature {
    public static double calculerPuissanceRadiateur(double tempInterieure, double tempCible, double volumePiece, double coeffDeperdition) {
        // 1. Si la température cible est déjà atteinte ou dépassée, on coupe le radiateur
        if (tempInterieure >= tempCible) {
            return 0.0;
        }

        // 2. Calcul du delta (écart de température)
        double delta = tempCible - tempInterieure;

        // 3. Application de la formule mathématique
        double puissanceBrute = Math.round(volumePiece * coeffDeperdition * delta) + 1000;

        // 4. On renvoie le résultat en s'assurant qu'il ne dépasse pas le maximum (1500W)
        return Math.min(1500.0, puissanceBrute);
    }
}