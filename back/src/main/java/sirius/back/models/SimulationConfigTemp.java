package sirius.back.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "simulation_config_temp")
public class SimulationConfigTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSimulation;

    @Column(name = "nom_config")
    private String nomConfig;

    @Column(name = "id_capteur_temp")
    private Integer idCapteurTemp;

    @Column(name = "etat_simulation")
    private Boolean etatSimulation;

    @Column(name = "envoi_bd")
    private Boolean envoiBd;

    // Paramètres de simulation
    private Double variationMaximum;
    private Double intervalleMinutes;
    private Double influenceHeure;
    private Double tempCible1;
    private Double tempCible2;
    private Double tempCible3;
    private Double tempCible4;

    public SimulationConfigTemp() {}

    public Long getIdSimulation() { return idSimulation; }
    public String getNomConfig() { return nomConfig; }
    public Integer getIdCapteurTemp() { return idCapteurTemp; }
    public Boolean getEtatSimulation() { return etatSimulation; }
    public Boolean getEnvoiBd() { return envoiBd; }
    public Double getVariationMaximum() { return variationMaximum; }
    public Double getIntervalleMinutes() { return intervalleMinutes; }
    public Double getInfluenceHeure() { return influenceHeure; }
    public Double getTempCible1() { return tempCible1; }
    public Double getTempCible2() { return tempCible2; }
    public Double getTempCible3() { return tempCible3; }
    public Double getTempCible4() { return tempCible4; }
}