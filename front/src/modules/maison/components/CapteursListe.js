export default function CapteurList({ capteurs }) {
    const listeTriee = [...capteurs].sort((a, b) => a.id_capteur_testpiece - b.id_capteur_testpiece);

    return (
        <div className="voir-plus-card">
            <h2>Capteurs</h2>
            <ul className="voir-plus-list">
                {listeTriee.map(function(c) {
                    return (
                        <li key={c.id_capteur_testpiece}>
                            <div className="voir-plus-item-info">
                                <span className="voir-plus-nom">{c.nom}</span>
                            </div>
                            <span className={c.etat === "ON" ? "etat-on" : "etat-off"}>
                                {c.etat}
                            </span>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
}