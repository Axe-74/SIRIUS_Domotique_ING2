import React from "react";
import { Link } from "react-router-dom";

export default function PanneauLateral({infoPiece, capteurs_testpiece, RATIO}) {
    let dansInfoPiece;

    if (infoPiece !== undefined) {
        let capteursDansPiece = capteurs_testpiece.filter(function (c) {
            return c.id_piece === infoPiece.id_piece;
        });
        dansInfoPiece = (
            <div className="conteneur-info-piece">
                <h4> Infos pièces </h4>
                <div>
                    <p> Etage : {infoPiece.etage} </p>
                    <p> Pièce : {infoPiece.nom} </p>
                    <p> Dimensions : {infoPiece.width / RATIO}m x {infoPiece.height / RATIO}m </p>
                    <h5> Capteur(s) </h5>
                    <ul>
                        {capteurs_testpiece
                            .filter(function (c) {
                                return c.id_piece === infoPiece.id_piece;
                            })
                            .map(function (c) {
                                return (
                                    <li key={c.id_capteur_testpiece}>
                                        {c.nom} : {c.etat}
                                    </li>
                                );
                            })
                        }
                    </ul>
                    <h5> Objet(s) </h5>
                    <ul>
                        {infoPiece.objets.map(function (o) {
                            return (
                                <li key={o.id_objet}>
                                    {o.nom_objet} : {o.etat ? 'ON' : 'OFF'}
                                </li>
                            );
                        })}
                    </ul>
                    <Link
                        to={"/piece/" + infoPiece.id_piece}
                        state={{ piece: infoPiece, capteurs: capteursDansPiece }}
                        className="lien-voir-plus"
                    >
                        Voir plus...
                    </Link>
                </div>
            </div>
        );
    } else {
        dansInfoPiece = null
    }
    return dansInfoPiece;
}