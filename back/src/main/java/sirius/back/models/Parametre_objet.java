package sirius.back.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import java.util.Map;


import javax.persistence.*;

@Entity
@Data
@Table(name = "parametre_objet")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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

    @Type(type = "jsonb")
    @Column(name = "specifications", columnDefinition = "jsonb")
    private Map<String, Object> donneesJson;

    public void setid_objet(Integer id_objet) {
        this.id_objet = id_objet;
    }

    public boolean getetat() {
        return etat;
    }

    public void setetat(boolean etat) {
        this.etat = etat;
    }

    public Map<String, Object> getDonneesJson() {
        return donneesJson;
    }

    public void setDonneesJson(Map<String, Object> donneesJson) {
        this.donneesJson = donneesJson;
    }
}
