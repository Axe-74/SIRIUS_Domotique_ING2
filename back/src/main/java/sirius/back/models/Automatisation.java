package sirius.back.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @Column(name = "heure_debut")
    private Integer heureDebut;

    @Column(name = "heure_fin")
    private Integer heureFin;

    @Column(name = "valeur_consigne")
    private Double valeurConsigne;

    @ManyToMany()
    @JsonIgnore
    @JoinTable(
            name = "automatisation_parametre_objet",
            joinColumns = @JoinColumn(name = "id_automatisation"),
            inverseJoinColumns = @JoinColumn(name = "id_objet")
    )
    private List<Parametre_objet> objetsRelies;

    public List<Parametre_objet> getObjetsRelies() { return objetsRelies; }

    public void setObjetsRelies(List<Parametre_objet> objetsRelies) { this.objetsRelies = objetsRelies; }

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