import React, {useEffect, useState} from 'react';
import { Stage, Layer, Rect, Text, Circle, Group } from 'react-konva';
import '../styles/Maison.css';
import {GET_PIECES} from "../constants/back";

var LARGEUR_ZONE = 800;
var HAUTEUR_ZONE = 600;
var ESPACE = 20;
var RATIO = 40;

var CAPTEURS = [
    { id_capteur: 1, id_piece: 1, nom: 'Capteur1', type: 'Température', etat: 'OFF' },
    { id_capteur: 2, id_piece: 1, nom: 'Capteur2', type: 'Lumière', etat: 'ON' },
    { id_capteur: 3, id_piece: 2, nom: 'Capteur3', type: 'Lumière', etat: 'ON' },
    { id_capteur: 4, id_piece: 3, nom: 'Capteur4', type: 'Mouvement', etat: 'ON' },
    { id_capteur: 5, id_piece: 1, nom: 'Capteur5', type: 'Mouvement', etat: 'ON' },
];


function verifierEtPlacer(listePieces) {

    var hauteurMax = 0;
    var currentX = ESPACE;
    var currentY = ESPACE;

    return listePieces.map((piece) => {

        var largeurPixels = piece.width * RATIO;
        var hauteurPixels = piece.height * RATIO;

        var xPixels = piece.x * RATIO;
        var yPixels = piece.y * RATIO;

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

        var pieceCalculee = {
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

    var [pieces, setPieces] = useState([]);
    var [idSelectionne, setIdSelectionne] = useState(null);

    useEffect(function() {
        fetch(GET_PIECES)
            .then(function(reponse) {
                return reponse.json();
            })
            .then(function(donnees) {
                var piecesCalculees = verifierEtPlacer(donnees);
                setPieces(piecesCalculees);
            });
    }, []);

    var messageVide = null;
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
            <h2>Plan de la Maison</h2>

            <div className="zone-plan-maison">
                <Stage width={LARGEUR_ZONE} height={HAUTEUR_ZONE}>
                    <Layer>
                        {pieces.map((piece, index) => {
                            var capteurs = CAPTEURS.filter(capteur => capteur.id_piece === piece.id_piece);

                            return (
                                <Group
                                    key={piece.id_piece}
                                    draggable
                                    x={piece.x}
                                    y={piece.y}

                                    onClick={() => setIdSelectionne(piece.id_piece)}

                                    dragBoundFunc={(pos) => {
                                        var newX = pos.x;
                                        var newY = pos.y;

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
                                        var copiePieces = [...pieces];
                                        copiePieces[index].x = e.target.x();
                                        copiePieces[index].y = e.target.y();
                                        setPieces(copiePieces);
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
                                    {capteurs.map((capteur, indexCapteur) => {
                                        var positionX = 15 + (indexCapteur * 18);
                                        var positionY = 15;

                                        return (
                                            <Circle
                                                key={capteur.id_capteur}
                                                x={positionX}
                                                y={positionY}
                                                radius={6}
                                                fill={capteur.etat === 'ON' ? "green" : "red"}                                                stroke="white"
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
    );
};