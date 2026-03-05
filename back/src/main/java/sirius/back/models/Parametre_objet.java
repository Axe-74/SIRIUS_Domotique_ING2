package sirius.back.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "parametre_objet")
public class Parametre_objet {

    @Id
    @Column(name="id_objet")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_objet;

    @Column(name = "nom_objet")
    private String nom_objet;

    @Column(name = "etat")
    private Boolean etat;

    public Integer getid_objet() {
        return id_objet;
    }

    public void setid_objet(Integer id_objet) {
        this.id_objet = id_objet;
    }

    public boolean getetat() {
        return etat;
    }

    public void setetat(boolean etat) {
        this.etat = etat;
    }
}
