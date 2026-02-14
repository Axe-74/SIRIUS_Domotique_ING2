package sirius.back.models;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "config_temp_anomalies")
@Getter
public class ConfigTempAnomalies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_config")
    private String nomConfig;

    @Column(name = "etat_activite")
    private Boolean etatActivite;

    @Column(name = "seuil_min_absolu")
    private Double seuilMinAbsolu;

    @Column(name = "seuil_max_absolu")
    private Double seuilMaxAbsolu;

    @Column(name = "nb_termes_moyenne")
    private Integer nbTermesMoyenne;

    @Column(name = "seuil_ecart_moyenne")
    private Double seuilEcartMoyenne;

    public ConfigTempAnomalies() {}

}