export default function ObjetList({ objets = [], historiqueHeures = {} }) {
    const sorted = [...objets].sort((a, b) => a.id_objet - b.id_objet);

    return (
        <div className="voir-plus-card">
            <h2>Objets</h2>
            <ul className="voir-plus-list">
                {sorted.map(function(o) {
                    let heureChangement = historiqueHeures[o.id_objet];
                    let texteHeure = heureChangement ? " (depuis " + heureChangement + ")" : "";
                    return (
                        <li key={o.id_objet}>
                            <div className="voir-plus-item-info">
                                <span className="voir-plus-nom">{o.nom_objet}</span>
                                {o.specifications && Object.keys(o.specifications).map(function(cle) {
                                    let valeur = o.specifications[cle];
                                    let nomAffiche = cle;
                                    let unite = "";

                                    // noms plus propres pour l'affichage
                                    if (cle === "temperature") {
                                        nomAffiche = "Température";
                                        unite = " °C";
                                    } else if (cle === "puissance_watts") {
                                        nomAffiche = "Puissance";
                                        unite = " W";
                                    } else if (cle === "degre_ouverture") {
                                        nomAffiche = "Degré d'ouverture";
                                        unite = " %";
                                    } else if (cle === "luminosite") {
                                        nomAffiche = "Luminosité";
                                        unite = " %";
                                    } else if (cle === "niveau_ouverture") {
                                        nomAffiche = "Niveau d'ouverture";
                                        unite = " %";
                                    }
                                    return (
                                        <span key={cle} className="voir-plus-spec">
                                            {nomAffiche} : {valeur + unite}
                                        </span>
                                    );
                                })}
                            </div>
                            <span className={o.etat ? "etat-on" : "etat-off"}>
                                {o.etat ? "ON" : "OFF"}
                                {texteHeure}
                            </span>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
}