import React, {useEffect, useState} from 'react';
import { Stage, Layer, Rect, Text, Circle, Group } from 'react-konva';
import '../styles/Maison.css';
import {GET_PIECES, GET_CAPTEURS_TESTPIECE, UPDATE_PIECE} from "../constants/back";

var LARGEUR_ZONE = 780;
var HAUTEUR_ZONE = 530;
var ESPACE = 20;
var RATIO = 40;


function verifierEtPlacer(listePieces) {

    var hauteurMax = 0;
    var currentX = ESPACE;
    var currentY = ESPACE;

    return listePieces.map(function(piece) {

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
    var [capteurs_testpiece, setCapteurs_testpiece] = useState([]);
    var [idSelectionne, setIdSelectionne] = useState(null);
    var [modifications, setModifications] = useState(false);

    useEffect(function() {
        fetch(GET_PIECES)
            .then(function(reponse) {
                return reponse.json();
            })
            .then(function(donnees) {
                var piecesCalculees = verifierEtPlacer(donnees);
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
        var intervalle = setInterval(rafraichirCapteurs, 5000);
        return function() {
            clearInterval(intervalle);
        };

    }, []);

    function sauvegarderPosition() {
        pieces.map(function(piece) {
            var pieceAEnvoyer = {
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
            <h3>Plan de la Maison</h3>
            <div className="zone-plan-maison">
                <Stage width={LARGEUR_ZONE} height={HAUTEUR_ZONE}>
                    <Layer>
                        {pieces.map(function(piece, index) {
                            var capteursDansPiece = capteurs_testpiece.filter(function(c) {
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
                                        var positionX = 15 + (indexCapteur * 18);
                                        var positionY = 15;

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
            {modifications === true && (
                <div style={{ display: 'flex', justifyContent: 'center'}}>
                    <button
                        onClick={sauvegarderPosition}
                        style={{
                            padding: '5px 20px',
                            backgroundColor: 'green',
                            color: 'white',
                            border: 'none',
                            cursor: 'pointer',
                            fontSize: '18px',
                            borderRadius: '5px'
                        }}
                    >
                        Sauvegarder
                    </button>
                </div>
            )}
        </div>
    );
};