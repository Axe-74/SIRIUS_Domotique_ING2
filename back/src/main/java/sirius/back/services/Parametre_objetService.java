package sirius.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.Parametre_objet;
import sirius.back.repositories.Parametre_objetRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Parametre_objetService {

    @Autowired
    private Parametre_objetRepository Parametre_objetRepository;

    @Transactional
    public void agirSurObjets(double temperature, LocalDateTime heure) {
        List<Parametre_objet> objets = Parametre_objetRepository.findAll();

        boolean Nuit = (heure.getHour() < 7 || heure.getHour() >= 21);
        boolean Matin = (heure.getHour() >= 7 && heure.getHour() < 12);
        boolean Après_midi = (heure.getHour() >= 12 && heure.getHour() < 19);
        boolean Soir = (heure.getHour() >= 19 && heure.getHour() < 21);
        double Volume_Salon = 4.5 * 3.6 * 2.2; //Longueur * Largeur * Hauteur
        int température_cible = 19;
        double Coefficient_de_deperdition = 1.2; // valeur moyenne d'un logement récent
        double tempDebutOuverture = 17.0; // À 17°C, on commence juste à ouvrir
        double tempMaxOuverture = 22.0;   // À 22°C, on ouvre au maximum
        int angleMax = 45;                // Angle maximum d'ouverture pris arbitrairement



        for (Parametre_objet objet : objets) {
            Map<String, Object> specs = objet.getDonneesJson();
            if (specs == null) specs = new HashMap<>();

            String nom = objet.getNom_objet().toLowerCase();

            if (nom.contains("radiateur")) {
                if (temperature < température_cible) {
                    double delta = température_cible - temperature;
                    double puissance = Math.min(1500,Math.round(Volume_Salon * Coefficient_de_deperdition * delta)+1000); // Puissance = Volume de la pièce x Coefficient de déperdition volumique G x Delta D. On rajoute 1000 pour une puissance plus cohérentes
                    objet.setetat(true);
                    specs.put("puissance_watts", puissance);
                } else {
                    objet.setetat(false);
                    specs.put("puissance_watts", 0);
                }
            }

            if (nom.contains("radiateur")) {
                if (temperature < température_cible) {
                    if (Nuit) {
                        objet.setetat(true);
                        specs.put("temperature", 19);
                    } else if (Matin) {
                        objet.setetat(true);
                        specs.put("temperature", 20);
                    } else if (Soir) {
                        objet.setetat(true);
                        specs.put("temperature", 21);
                    } else {
                        objet.setetat(false);
                        specs.put("temperature", 15);
                    }
                }
            }

            else if (nom.contains("volet")) {
                if (Nuit) {
                    specs.put("niveau_ouverture", 0);
                    specs.put("statut", "Fermé");
                } else if (Après_midi){
                    specs.put("niveau_ouverture", 100);
                    specs.put("statut", "Ouvert");
                } else if (Soir) {
                    specs.put("niveau_ouverture", 70);
                    specs.put("statut", "Ouvert");
                }
                else  {
                    specs.put("niveau_ouverture", 30);
                    specs.put("statut", "Veille");
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
                    // S'il fait moins de 17°C ou que ce n'est pas l'après-midi
                    objet.setetat(false);
                    specs.put("degre_ouverture", 0);
                    specs.put("statut", "Fermée");
                }
            }

            else if (nom.contains("clim")) {
                if (temperature > 24.0) {
                    objet.setetat(true);
                    specs.put("mode", "Froid");
                } else {
                    objet.setetat(false);
                    specs.put("mode", "Arrêt");
                }
            }

            else if (nom.contains("lumiere")) {
                if (Matin) {
                    objet.setetat(true);
                    specs.put("luminosite", 30);
                } else if (Après_midi) {
                    objet.setetat(false);
                    specs.put("luminosite", 0);
                } else if (Soir) {
                    objet.setetat(true);
                    specs.put("luminosite", 100);
                }
                else {
                    objet.setetat(false);
                    specs.put("luminosite", 0);
                }
            }
            System.out.println(specs);


            // On sauvegarde le JSON dans l'objet
            //objet.setDonneesJson(specs);
        }
        //System.out.println(heure);



        Parametre_objetRepository.saveAll(objets);
    }
}

