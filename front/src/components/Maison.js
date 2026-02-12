import React, {useEffect, useState} from 'react';
import { Stage, Layer, Rect, Text, Circle, Group } from 'react-konva';
import '../styles/Maison.css';
import {GET_PIECES, GET_CAPTEURS_TESTPIECE, UPDATE_PIECE} from "../constants/back";

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

export default function Maison() {

    const [pieces, setPieces] = useState([]);
    const [capteurs_testpiece, setCapteurs_testpiece] = useState([]);
    const [idSelectionne, setIdSelectionne] = useState(null);
    const [modifications, setModifications] = useState(false);
    const [etageActuel, setEtageActuel] = useState(0);

    useEffect(function() {
        fetch(GET_PIECES)
            .then(function(reponse) {
                return reponse.json();
            })
            .then(function(donnees) {
                const piecesCalculees = verifierEtPlacer(donnees);
                setPieces(piecesCalculees);
            });

        function rafraichirCapteurs() {
            fetch(GET_CAPTEURS_TESTPIECE)
                .then(function(reponse) {
                    return reponse.json();
                })
                .then(function(donnees) {
                    setCapteurs_testpiece(donnees);
                })
        }
        rafraichirCapteurs();
        const intervalle = setInterval(rafraichirCapteurs, 5000);
        return function() {
            clearInterval(intervalle);
        };

    }, []);

    function sauvegarderPosition() {
        pieces.forEach(function(piece) {
            const pieceAEnvoyer = {
                ...piece,
                x: piece.x / RATIO,
                y: piece.y / RATIO,
                width: piece.width / RATIO,
                height: piece.height / RATIO
            };

            fetch(UPDATE_PIECE, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(pieceAEnvoyer)
            });
        });

        setModifications(false);
    }

    const listeEtages = [0];
    pieces.forEach(function(piece) {
        if (listeEtages.includes(piece.etage) === false) {
            listeEtages.push(piece.etage);
        }
    });
    listeEtages.sort();

    const listeEtagesInversee = [...listeEtages].reverse();

    const piecesAffichees = pieces.filter(function (piece) {
        return piece.etage === etageActuel;
    });

    let messageVide = null;
    if (pieces.length === 0) {
        messageVide = (
            <Text
                text="Aucune pièce enregistrée."
                fontSize={24}
                fill="gray"
                width={LARGEUR_ZONE}
                y={HAUTEUR_ZONE / 2}
                align="center"
            />
        );
    }

    return (
        <div className="interface-maison">
            <h3>Plan de la Maison</h3>
            <div className="conteneur-maison-boutons">
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
                <div className="zone-plan-maison">
                    <Stage width={LARGEUR_ZONE} height={HAUTEUR_ZONE}>
                        <Layer>
                            {piecesAffichees.map(function(piece, index) {
                                const capteursDansPiece = capteurs_testpiece.filter(function (c) {
                                    return c.id_piece === piece.id_piece;
                                });
                                return (
                                    <Group
                                        key={piece.id_piece}
                                        draggable
                                        x={piece.x}
                                        y={piece.y}

                                        onClick={() => setIdSelectionne(piece.id_piece)}

                                        dragBoundFunc={(pos) => {
                                            let newX = pos.x;
                                            let newY = pos.y;

                                            if (newX < 0) {
                                                newX = 0;
                                            }
                                            if (newX > LARGEUR_ZONE - piece.width) {
                                                newX = LARGEUR_ZONE - piece.width;
                                            }
                                            if (newY < 0) {
                                                newY = 0;
                                            }
                                            if (newY > HAUTEUR_ZONE - piece.height) {
                                                newY = HAUTEUR_ZONE - piece.height;
                                            }
                                            return { x: newX, y: newY };
                                        }}

                                        onDragEnd={(e) => {
                                            const nouvelleListe = pieces.map(function (p) {
                                                if (p.id_piece === piece.id_piece) {
                                                    return {
                                                        ...p,
                                                        x: e.target.x(),
                                                        y: e.target.y()
                                                    };
                                                } else {
                                                    return p;
                                                }
                                            });

                                            setPieces(nouvelleListe);
                                            setModifications(true);
                                        }}
                                    >
                                        <Rect
                                            width={piece.width}
                                            height={piece.height}
                                            fill={idSelectionne === piece.id_piece ? "purple" : "blue"}
                                            stroke="black"
                                            strokeWidth={2}
                                        />
                                        <Text
                                            text={piece.nom}
                                            fontSize={18}
                                            fontStyle="bold"
                                            fill="white"
                                            width={piece.width}
                                            height={piece.height}
                                            align="center"
                                            verticalAlign="middle"
                                        />
                                        {capteursDansPiece.map(function(capteur, indexCapteur) {
                                            const positionX = 15 + (indexCapteur * 18);
                                            const positionY = 15;

                                            return (
                                                <Circle
                                                    key={capteur.id_capteur_testpiece}
                                                    x={positionX}
                                                    y={positionY}
                                                    radius={6}
                                                    fill={capteur.etat === 'ON' ? "green" : "red"}
                                                    stroke="white"
                                                    strokeWidth={1}
                                                />
                                            );
                                        })}
                                    </Group>
                                );
                            })}
                            {messageVide}
                        </Layer>
                    </Stage>
                </div>
            </div>
            {modifications === true && (
                <div className="conteneur-sauvegarde">
                    <button className="bouton-sauvegarde"
                        onClick={sauvegarderPosition}
                    >
                        Sauvegarder
                    </button>
                </div>
            )}
        </div>
    );
};
