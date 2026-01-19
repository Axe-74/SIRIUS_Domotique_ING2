package sirius.back.models.sample;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Autmatisation")
public class Automatisation {

    @Id
    @Column(name="id_automatisation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutomatisation;

    @Column(name = "etat")
    private Boolean etats;

    @Column(name = "nom")
    private String nom;

    public Integer getid_Automatisation() {
        return idAutomatisation;
    }

    public void setid_automatisation(Integer idAutomatisation) {
        this.idAutomatisation = idAutomatisation;
    }

    public boolean getetat() {
        return etats;
    }

    public void setetat(boolean etat) {
        this.etats = etat;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }
}