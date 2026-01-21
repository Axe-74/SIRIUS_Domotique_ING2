package sirius.back.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Automatisation")
public class Automatisation {

    @Id
    @Column(name="id_automatisation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutomatisation;

    @Column(name = "etat")
    private Boolean etats;

    @Column(name = "nom")
    private String nom;


    @Column(name = "seuil_de_declenchement")
    private double seuilDeDeclenchement;

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

    public double getseuil_de_declenchement() {
        return seuilDeDeclenchement;
    }

    public void setseuil_de_declenchement(double seuilDeDeclenchement) {
        this.seuilDeDeclenchement = seuilDeDeclenchement;
    }
}