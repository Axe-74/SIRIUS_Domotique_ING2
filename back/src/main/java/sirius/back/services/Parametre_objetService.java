package sirius.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sirius.back.models.Parametre_objet;
import sirius.back.repositories.Parametre_objetRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Parametre_objetService {

    @Autowired
    private Parametre_objetRepository parametreObjetRepository;

    @Transactional
    public void mettreAJourSpecification() {
        List<Parametre_objet> parametreObjets = parametreObjetRepository.findAll();
        for (Parametre_objet parametreObjet : parametreObjets) {
            Map<String, Object> specs = parametreObjet.getDonneesJson();
            if (specs == null) {
                specs = new HashMap<>();
            }
            specs.put("puissance", 1600);
            specs.put("mode", "Eco");

            parametreObjet.setDonneesJson(specs);
            parametreObjetRepository.save(parametreObjet);
        }
    }
}