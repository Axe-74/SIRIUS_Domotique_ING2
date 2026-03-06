import React from "react";


export default function Etage({listeEtagesInversee, etageActuel, setEtageActuel}) {
    return (
        <div className="conteneur-etage">

            {listeEtagesInversee.map(function(etage) {
                return (
                    <button
                        key={etage}
                        onClick={() => setEtageActuel(etage)}
                        className={etage === etageActuel ? "bouton-etage actuel" : "bouton-etage"}
                    >
                        {etage}
                    </button>
                )
            })}
        </div>
    )
}