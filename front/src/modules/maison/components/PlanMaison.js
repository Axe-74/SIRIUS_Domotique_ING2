import React from "react";
import {Circle, Group, Layer, Rect, Stage, Text} from "react-konva";

export default function PlanMaison({LARGEUR_ZONE, HAUTEUR_ZONE, piecesAffichees, capteurs_testpiece,
                                       idSelectionne, setIdSelectionne, pieces, setPieces, setModifications, messageVide}) {
    return (
        <div className="zone-plan-maison">
            <Stage width={LARGEUR_ZONE} height={HAUTEUR_ZONE}>
                <Layer>
                    {piecesAffichees.map(function(piece) {
                        const capteursDansPiece = capteurs_testpiece.filter(function (c) {
                            return c.id_piece === piece.id_piece;
                        });
                        return (
                            <Group
                                key={piece.id_piece}
                                draggable
                                x={piece.x}
                                y={piece.y}

                                onClick={() => {
                                    if (idSelectionne === piece.id_piece) {
                                        setIdSelectionne(null);
                                    } else {
                                        setIdSelectionne(piece.id_piece);
                                    }
                                }}


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
    )
}