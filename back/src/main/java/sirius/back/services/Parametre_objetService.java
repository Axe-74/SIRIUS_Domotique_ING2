package sirius.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.Parametre_objet;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.Parametre_objetRepository;
import sirius.back.repositories.mesure_v1Repository;
import sirius.back.utils.fonctionpure.CalculTemperature;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Parametre_objetService {

    @Autowired
    private Parametre_objetRepository Parametre_objetRepository;
    @Autowired
    private mesure_v1Repository mesure_v1Repository;

    @Transactional
    public void agirSurObjets(double temperature, LocalDateTime heure) {
        List<Parametre_objet> objets = Parametre_objetRepository.findAll();
        mesure_v1 mesure_v1_interieur = mesure_v1Repository.findLatestMesureByCapteurOrder(11);
        mesure_v1 mesure_v1_mouvement = mesure_v1Repository.findLatestMesureByCapteurOrder(3);

        // --- Paramètres temporels ---
        boolean Nuit = (heure.getHour() < 7 || heure.getHour() >= 21);
        boolean Matin = (heure.getHour() >= 7 && heure.getHour() < 12);
        boolean Après_midi = (heure.getHour() >= 12 && heure.getHour() < 19);
        boolean Soir = (heure.getHour() >= 19 && heure.getHour() < 21);

        // --- Paramètres physiques ---
        double Volume_Salon = 4.5 * 3.6 * 2.2;
        int température_cible = 19;
        double Coefficient_de_deperdition = 1.2;
        double tempDebutOuverture = 17.0;
        double tempMaxOuverture = 22.0;
        int angleMax = 45;

        double TemperatureInterieure = (double) mesure_v1_interieur.getValeur();
        boolean DetectionMouvement = mesure_v1_mouvement.getValeur() != 0.0;

        boolean conditionsOuvertureFenetre = Après_midi && temperature >= tempDebutOuverture && TemperatureInterieure >= 18.0;

        for (Parametre_objet objet : objets) {
            Map<String, Object> specs = objet.getDonneesJson();
            if (specs == null) specs = new HashMap<>();

            String nom = objet.getNom_objet().toLowerCase();

            if (nom.contains("radiateur")) {
                if (temperature < température_cible && !conditionsOuvertureFenetre) {
                    double puissance = CalculTemperature.calculerPuissanceRadiateur(
                            temperature,
                            température_cible,
                            Volume_Salon,
                            Coefficient_de_deperdition
                    );

                    objet.setetat(true);
                    specs.put("puissance_watts", puissance);
                    specs.put("statut", "Allume");

                    if (Nuit) specs.put("temperature", 19);
                    else if (Matin) specs.put("temperature", 20);
                    else if (Soir) specs.put("temperature", 21);
                    else specs.put("temperature", 15);

                } else {
                    objet.setetat(false);
                    specs.put("puissance_watts", 0);
                    specs.put("statut", "Eteint");
                }
            }

            else if (nom.contains("fenêtre")) {
                if (Après_midi && temperature >= tempDebutOuverture) {
                    objet.setetat(true);

                    if (temperature >= tempMaxOuverture) {
                        specs.put("degre_ouverture", angleMax);
                        specs.put("statut", "Aération maximale");
                    } else {
                        double plageTemperature = tempMaxOuverture - tempDebutOuverture;
                        double depassement = temperature - tempDebutOuverture;
                        double ratioOuverture = depassement / plageTemperature;

                        int ouverture = (int) Math.round(ratioOuverture * angleMax);

                        specs.put("degre_ouverture", ouverture);
                        specs.put("statut", "Aération progressive");
                    }
                } else {
                    objet.setetat(false);
                    specs.put("degre_ouverture", 0);
                    specs.put("statut", "Fermée");
                }
            }

            else if (nom.contains("volet")) {
                if (Nuit) specs.put("niveau_ouverture", 0);
                else if (Après_midi) specs.put("niveau_ouverture", 100);
                else if (Soir) specs.put("niveau_ouverture", 70);
                else specs.put("niveau_ouverture", 30);
            }
            else if (nom.contains("clim")) {
                if (TemperatureInterieure > 24.0) {
                    objet.setetat(true);
                    specs.put("mode", "Froid");
                } else {
                    objet.setetat(false);
                    specs.put("mode", "Arrêt");
                }
            }
            else if (nom.contains("lumiere")) {
                if (DetectionMouvement) {
                    objet.setetat(true);
                    specs.put("luminosite", Nuit ? 30 : 100);
                } else {
                    objet.setetat(false);
                    specs.put("luminosite", 0);
                }
            }

            objet.setDonneesJson(specs);
        }
        System.out.println(heure);
        Parametre_objetRepository.saveAll(objets);
    }
}

