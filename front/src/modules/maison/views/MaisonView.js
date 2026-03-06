import React, {useEffect, useState} from 'react';
import { Text } from 'react-konva';
import '../../../styles/Maison.css';

import { fetchPieces, fetchCapteurs, sauvegarderPosition } from '../api/maisonApi';

import Etage from '../components/Etage';
import PlanMaison from '../components/PlanMaison';
import PanneauLateral from '../components/PanneauLateral';
import BoutonSauvegarde from '../components/BoutonSauvegarde';

const LARGEUR_ZONE = 780;
const HAUTEUR_ZONE = 530;
const ESPACE = 20;
const RATIO = 40;

function verifierEtPlacer(listePieces) {

    let hauteurMax = 0;
    let currentX = ESPACE;
    let currentY = ESPACE;

    return listePieces.map(function(piece) {

        const largeurPixels = piece.width * RATIO;
        const hauteurPixels = piece.height * RATIO;

        const xPixels = piece.x * RATIO;
        const yPixels = piece.y * RATIO;

        if (xPixels !== 0 || yPixels !== 0) {
            return {
                ...piece,
                width: largeurPixels,
                height: hauteurPixels,
                x: xPixels,
                y: yPixels,
            }
        }

        if (hauteurPixels > hauteurMax) {
            hauteurMax = hauteurPixels;
        }

        if (currentX + largeurPixels > LARGEUR_ZONE) {
            currentY = currentY + hauteurMax + ESPACE;
            currentX = ESPACE;
            hauteurMax = 0;
        }

        const pieceCalculee = {
            ...piece,
            width: largeurPixels,
            height: hauteurPixels,
            x: currentX,
            y: currentY
        };

        currentX = currentX + largeurPixels + ESPACE;

        return pieceCalculee;
    });
}


export default function MaisonView() {
    const [pieces, setPieces] = useState([]);
    const [capteurs_testpiece, setCapteurs_testpiece] = useState([]);
    const [idSelectionne, setIdSelectionne] = useState(null);
    const [modifications, setModifications] = useState(false);
    const [etageActuel, setEtageActuel] = useState(0);

    useEffect(function() {
        fetchPieces().then(function(donnees) {
                const piecesCalculees = verifierEtPlacer(donnees);
                setPieces(piecesCalculees);
            });

        function rafraichirCapteurs() {
            fetchCapteurs().then(function(donnees) {
                    setCapteurs_testpiece(donnees);
                })
        }
        rafraichirCapteurs();
        const intervalle = setInterval(rafraichirCapteurs, 5000);
        return function() {
            clearInterval(intervalle);
        };

    }, []);

    function sauvegarderDeplacementPosition() {
        pieces.forEach(function(piece) {
            const pieceAEnvoyer = {
                ...piece,
                x: piece.x / RATIO,
                y: piece.y / RATIO,
                width: piece.width / RATIO,
                height: piece.height / RATIO
            };
            sauvegarderPosition(pieceAEnvoyer);
        });
        setModifications(false);
    }

    const listeEtages = [0];
    pieces.forEach(function (piece) {
        if (!listeEtages.includes(piece.etage)) {
            listeEtages.push(piece.etage);
        }
    });
    listeEtages.sort();
    const listeEtagesInversee = [...listeEtages].reverse();

    const piecesAffichees = pieces.filter(function (piece) {
        return piece.etage === etageActuel;
    });

    const infoPiece = pieces.find(function (piece) {
        return piece.id_piece === idSelectionne;
    });

    let messageVide = null;
    if (pieces.length === 0) {
        messageVide = (
            <Text
                text="Aucune pièce enregistrée."
                fontSize={24} fill="gray" width={LARGEUR_ZONE} y={HAUTEUR_ZONE / 2} align="center"
            />
        );
    }

    return (
        <div className="interface-maison">
            <h3>Plan de la Maison</h3>
            <div className="conteneur-maison-boutons">
                <Etage
                    listeEtagesInversee={listeEtagesInversee}
                    etageActuel={etageActuel}
                    setEtageActuel={setEtageActuel}
                />
                <PlanMaison
                    LARGEUR_ZONE={LARGEUR_ZONE}
                    HAUTEUR_ZONE={HAUTEUR_ZONE}
                    piecesAffichees={piecesAffichees}
                    capteurs_testpiece={capteurs_testpiece}
                    idSelectionne={idSelectionne}
                    setIdSelectionne={setIdSelectionne}
                    pieces={pieces}
                    setPieces={setPieces}
                    setModifications={setModifications}
                    messageVide={messageVide}
                />
                <div>
                    <PanneauLateral
                        infoPiece={infoPiece}
                        capteurs_testpiece={capteurs_testpiece}
                        RATIO={RATIO}
                    />
                </div>
            </div>
            <BoutonSauvegarde
                modifications={modifications}
                onClickSauvegarde={sauvegarderDeplacementPosition}
            />
        </div>
    );
}