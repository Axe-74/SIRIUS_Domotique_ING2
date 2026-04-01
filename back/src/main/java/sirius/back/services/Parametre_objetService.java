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

        boolean Nuit = (heure.getHour() < 7 || heure.getHour() > 22);
        boolean Soir = (heure.getHour() >= 18 && heure.getHour() <= 23);
        boolean Jour = (heure.getHour() >= 7 && heure.getHour() < 18);


        for (Parametre_objet objet : objets) {
            Map<String, Object> specs = objet.getDonneesJson();
            if (specs == null) specs = new HashMap<>();

            String nom = objet.getNom_objet().toLowerCase();

            if (nom.contains("fenêtre")) {
                if (temperature > 22.0 && temperature < 26.0 && !Nuit) {
                    specs.put("degre_ouverture", 30);
                } else {
                    specs.put("degre_ouverture", 0);
                }
            }
            else if (nom.contains("volet")) {
                if (Nuit) {
                    specs.put("niveau_ouverture", 0);
                } else if (temperature > 27.0) {
                    specs.put("niveau_ouverture", 20);
                } else {
                    specs.put("niveau_ouverture", 100);
                }
            }
            else if (nom.contains("radiateur")) {
                if (temperature < 19.0) {
                    objet.setetat(true);
                    specs.put("puissance", 1500);
                    specs.put("temperature", 21.0);
                } else {
                    objet.setetat(false);
                    specs.put("puissance", 0);
                    specs.put("temperature", 18.0);
                }
            }
            else if (nom.contains("Climatisation")) {
                if (temperature > 25.0) {
                    objet.setetat(true);
                    specs.put("temperature", 22.0);
                } else {
                    objet.setetat(false);
                }
            }
            else if (nom.contains("lumiere")) {
                boolean periode = Soir || Nuit;
                objet.setetat(periode);
                specs.put("allumee", periode);
                if (periode = Soir) {
                    specs.put("intensite", 100);                    
                }
                else if (periode = Nuit) {
                    specs.put("intensite", 30);
                }
                

            }
            //System.out.println(specs);
            objet.setDonneesJson(specs);
        }

        Parametre_objetRepository.saveAll(objets);
    }}