package sirius.back;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sirius.back.mock.TempInterieureSimulation;

public class TempInterieureSimulationTest {

    private final TempInterieureSimulation tempInterieureSimulation = new TempInterieureSimulation();

    @Test
    public void testCalculerNouvelleTemperatureAugmentation() {
        double tempActuelle = 15.0;
        double tempExt = 5.0;
        Double radiateurCible = 22.0;
        double fenetreOuverte = 0.0;
        double resultat = tempInterieureSimulation.calculerNouvelleTemperature(radiateurCible, fenetreOuverte, tempActuelle, tempExt);
        assertTrue(resultat > tempActuelle, "Radiateur allumé, donc la température monte");
    }

    @Test
    public void testCalculerNouvelleTemperatureDiminution() {
        double tempActuelle = 20.0;
        double tempExt = 10.0;
        Double radiateurCible = null;
        double fenetreOuverte = 0.0;
        double resultat = tempInterieureSimulation.calculerNouvelleTemperature(radiateurCible, fenetreOuverte, tempActuelle, tempExt);
        assertTrue(resultat < tempActuelle, "Radiateur éteint, fenêtre ouverte, donc la température descend");
    }

    @Test
    public void testCalculerNouvelleTemperatureStagnation() {
        double tempActuelle = 20.0;
        double tempExt = 20.0;
        Double radiateurCible = 20.0;
        double fenetreOuverte = 0.0;
        double resultat = tempInterieureSimulation.calculerNouvelleTemperature(radiateurCible, fenetreOuverte, tempActuelle, tempExt);
        assertTrue(resultat == tempActuelle, "Températures extérieure, du radiateur et intérieure égales, fenêtre fermée, donc la température stagne");
    }


}