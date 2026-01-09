package sirius.back.models.sample;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "mesure_v1")
public class mesure_v1 {
    @Id
    @Column(name="id_mesure")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mesure;

    @Column(name = "valeur")
    private Float valeur;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "id_capteur")
    private Integer id_capteur;

    public Integer getId_mesure() {
        return id_mesure;
    }

    public void setId_mesure(Integer id_mesure) {
        this.id_mesure = id_mesure;
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

    public Integer getId_capteur() {
        return id_capteur;
    }

    public void setId_capteur(Integer id_capteur) {
        this.id_capteur = id_capteur;
    }

}
