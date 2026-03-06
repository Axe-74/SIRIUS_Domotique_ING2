package sirius.back.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "mesure_v1")
public class mesure_v1 {
    @Id
    @Column(name="idMesure")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMesure;

    @Column(name = "valeur")
    private Float valeur;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "idCapteur")
    private Integer idCapteur;

    public Integer getIdMesure() {
        return idMesure;
    }

    public void setIdMesure(Integer idMesure) {
        this.idMesure = idMesure;
    }

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(Integer idCapteur) {
        this.idCapteur = idCapteur;
    }

}
