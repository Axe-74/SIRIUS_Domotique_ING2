package sirius.back.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.List;
import java.util.Map;

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

    @Type(type = "jsonb")
    @Column(name = "specifications", columnDefinition = "jsonb")
    private Map<String, Object> specifications;

    @ToString.Exclude // pour prevent une boucle infinie
    @ManyToMany(mappedBy = "objets", fetch = FetchType.EAGER)
    private List<Piece> pieces;

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
        return specifications;
    }

    public void setDonneesJson(Map<String, Object> specifications) {
        this.specifications = specifications;
    }
}
