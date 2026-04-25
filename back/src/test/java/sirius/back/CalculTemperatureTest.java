package sirius.back;

import org.junit.jupiter.api.Test;
import sirius.back.utils.fonctionpure.CalculTemperature;

import static org.junit.jupiter.api.Assertions.*;

public class CalculTemperatureTest {

    @Test
    public void testCalculerPuissanceRadiateurDoitChauffer() {
        // Test normal : Il fait 15°C, on veut 19°C (Delta = 4)
        // Calcul manuel attendu : (35.64 * 1.2 * 4) = 171.07 -> Arrondi(171) + 1000 = 1171.0
        double puissance = CalculTemperature.calculerPuissanceRadiateur(15.0, 19.0, 35.64, 1.2);

        assertEquals(1171.0, puissance, "La puissance calculée devrait être de 1171W");
    }

    @Test
    public void testCalculerPuissanceRadiateurPlafondMaximum() {
        // Test extrême : Il fait 0°C, on veut 20°C (Delta = 20)
        // La formule brute donnerait (50 * 1.2 * 20) + 1000 = 2200W.
        // Notre fonction doit bloquer à 1500W.
        double puissance = CalculTemperature.calculerPuissanceRadiateur(0.0, 20.0, 50.0, 1.2);

        assertEquals(1500.0, puissance, "La puissance ne doit pas dépasser le plafond de 1500W");
    }

    @Test
    public void testCalculerPuissanceRadiateurTempAtteinte() {
        // Test d'arrêt : Il fait 20°C, on veut 19°C
        double puissance = CalculTemperature.calculerPuissanceRadiateur(20.0, 19.0, 35.64, 1.2);

        assertEquals(0.0, puissance, "Le radiateur doit être éteint (0W) si la température est atteinte");
    }
}