package sirius.back.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sirius.back.models.Capteur;
import sirius.back.models.Parametre_objet;
import sirius.back.models.SimulationConfigTemp;
import sirius.back.models.mesure_v1;
import sirius.back.repositories.CapteurRepository;
import sirius.back.repositories.Parametre_objetRepository;
import sirius.back.repositories.SimulationConfigTempRepository;
import sirius.back.services.mesure_v1Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class TempInterieureSimulation {

    @Autowired
    private mesure_v1Service mesureService;

    @Autowired
    private SimulationConfigTempRepository configRepository;

    @Autowired
    private CapteurRepository capteurRepository;

    @Autowired
    private Parametre_objetRepository objetRepository;

    private Double tempActuelle = null;
    private Double tempExterieure = null;

    // Coefficients influence variables sur temp intérieure
    private final double COEF_PUISSANCE_RADIATEUR = 0.05;
    private final double COEF_INFLUENCE_EXTERIEURE_MAX = 0.1;
    private final double COEF_ISOLATION_FERMEE = 0.001;

    public void lancerSimulation() {
        Random random = new Random();

        try {
            SimulationConfigTemp config = configRepository.findTopByEtatSimulationTrueOrderByIdSimulationDesc();

            if (config == null) {
                System.err.println("Pas de config trouvée");
                return;
            }

            System.out.println("Démarrage de la simulation d'une journée : température intérieure");
            System.out.println("Configuration utilisée : " + config.getNomConfig());

            // 1. Initialisation de l'heure
            LocalDateTime heureFake = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);

            double tempCible;

            int nbMesures = 1440;
            long tempsPause = 200;

            int idCapteurExterieur =config.getIdCapteurTemp() + 1 ;
            int idCapteurInterieur = config.getIdCapteurTemp() + 10;

            // 2. Récupération des obets dans la pièce
            List<Parametre_objet> objetsPiece = null;
            Capteur capteurInt = capteurRepository.findById(idCapteurInterieur).orElse(null);
            if (capteurInt != null && capteurInt.getPiece() != null) {
                objetsPiece = objetRepository.findByPieces(capteurInt.getPiece());
            }

            if (tempActuelle == null) {
                int heure = heureFake.getHour();
                int minute = heureFake.getMinute();
                int seconde = heureFake.getSecond();
                mesure_v1 lastMesure = mesureService.findLatestMesureByCapteur(idCapteurInterieur);

                if (heure == 0 && minute == 0 && seconde == 0) {
                    tempActuelle = config.getTempCible4();
                } else if (lastMesure != null) {
                    tempActuelle = (double) lastMesure.getValeur();
                } else {
                    tempActuelle = 10.0;
                }
            }

//            List<Parametre_objet> objall = objetRepository.findAll();
//            for  (Parametre_objet objet : objall) {
//                System.out.println(objet.getPieces());
//            }

            for (int i = 0; i < nbMesures; i++) {

                mesure_v1 lastMesure = mesureService.findLatestMesureByCapteur(idCapteurExterieur);
                tempExterieure = (double) lastMesure.getValeur();

                // 4. Analyse des objets
                Double tempCibleRadiateur = null;
                double tauxOuvertureFenetre = 0.0;
                boolean radiateurTrouve = false;

                if (objetsPiece != null) {
                    for (Parametre_objet obj : objetsPiece) {

                        if (!obj.getetat()) {
                            continue;
                        }

                        String nomObjet = obj.getNom_objet().toLowerCase();
                        Map<String, Object> specs = obj.getSpecifications();

                        // Variable 1 : radiateur
                        if (nomObjet.contains("radiateur")) {
                            Double tempRadiateur = null;
                            if (specs != null && specs.containsKey("temperature")) {
                                radiateurTrouve = true;
                                tempRadiateur = Double.valueOf(specs.get("temperature").toString());
                            }

                            if (tempRadiateur != null) {
                                if (tempCibleRadiateur == null || tempRadiateur > tempCibleRadiateur) {
                                    tempCibleRadiateur = tempRadiateur;
                                }
                            }
                        }

                        // Variable 2 : fenêtre
                        if (nomObjet.contains("fenêtre")) {
                            double tauxFenetre = 0.0;
                            if (specs != null && specs.containsKey("degre_ouverture")) {
                                double pourcentageOuverture = Double.parseDouble(specs.get("degre_ouverture").toString());
                                tauxFenetre = pourcentageOuverture / 100.0;
                            } else {
                                tauxFenetre = 1.0;
                            }
                            tauxOuvertureFenetre += tauxFenetre;
                        }
                    }
                }

                if (radiateurTrouve && tempCibleRadiateur == null) {
                    tempCibleRadiateur = 22.0;
                }
                if (tauxOuvertureFenetre > 1.0) {
                    tauxOuvertureFenetre = 1.0;
                }

                // 5. Calcul de la nouvelle température
                if (tempCibleRadiateur != null) {

                    // Cas 1 : radiateur allumé, extérieur a 0 influence en comparaison
                    double difference = tempCibleRadiateur - tempActuelle;
                    tempActuelle += (difference * COEF_PUISSANCE_RADIATEUR);

                } else {

                    // Cas 2 : radiateur éteint, extérieur prime
                    if (tauxOuvertureFenetre > 0) {
                        double influence = COEF_INFLUENCE_EXTERIEURE_MAX * tauxOuvertureFenetre;
                        tempActuelle += (tempExterieure - tempActuelle) * influence;
                    } else {
                        tempActuelle += (tempExterieure - tempActuelle) * COEF_ISOLATION_FERMEE;
                    }
                }

                // 6. Bruit aléatoire du capteur
                double variationAleatoire = (random.nextDouble() * 2 - 1) * config.getVariationMaximum();
                tempActuelle += variationAleatoire;

                double tempArrondie = Math.round(tempActuelle * 100.0) / 100.0;

                // 7. Enregistrement en BDD
                mesure_v1 nouvelleMesure = new mesure_v1();
                nouvelleMesure.setValeur((float) tempArrondie);
                nouvelleMesure.setIdCapteur(idCapteurInterieur);
                nouvelleMesure.setDate(heureFake);

                mesureService.ajouterMesure(nouvelleMesure);

                heureFake = heureFake.plusMinutes(1);
                try {
                    Thread.sleep(tempsPause);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println("Journée simulée avec succès !");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}