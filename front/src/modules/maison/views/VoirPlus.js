import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { fetchMesureCapteur, fetchPieceById } from '../api/maisonApi';
import CapteursListe from '../components/CapteursListe';
import ObjetsListe from '../components/ObjetsListe';
import '../../../styles/Maison.css';

export default function VoirPlus() {
    const location = useLocation();
    const navigate = useNavigate();

    const [infoPiece, setInfoPiece] = useState(location.state ? location.state.piece : undefined);
    const [capteurs, setCapteurs] = useState(location.state ? location.state.capteurs : []);
    const [meteo, setMeteo] = useState({ heure: "", temp: "" });

    // meteo et horloge depuis capteur
    useEffect(() => {
        const rafraichirMeteo = () => {
            fetchMesureCapteur(2).then(function(data) {
                if (data && data.date) {
                    setMeteo({
                        heure: data.date.substring(11, 16),
                        temp: data.valeur
                    });
                }
            });
        }

        rafraichirMeteo();
        const timer = setInterval(rafraichirMeteo, 300);
        return () => clearInterval(timer);
    }, []);

    // données de la piece
    useEffect(function() {
        if (infoPiece !== undefined && infoPiece.id_piece !== undefined) {
            function rafraichirPiece() {
                fetchPieceById(infoPiece.id_piece).then(function(data) {
                    if (data) {
                        setInfoPiece(data);
                        if (data.capteurs) {
                            setCapteurs(data.capteurs);
                        }
                    }
                });
            }
            const timer = setInterval(rafraichirPiece, 300);
            return function() {
                clearInterval(timer);
            };
        }
    }, [infoPiece]);

    function clicRetour() {
        navigate('/maison');
    }

    if (infoPiece === undefined) {
        return (
            <div className="voir-plus-container">
                <p>Erreur : Pièce introuvable.</p>
                <button onClick={clicRetour}>Retour au plan</button>
            </div>
        );
    }

    return (
        <div className="voir-plus-container">
            <div className="voir-plus-header">
                <button onClick={clicRetour}>Retour au plan</button>
                <div className="voir-plus-titre">
                    <h1>Détails de la pièce : {infoPiece.nom}</h1>
                    <p>Etage : {infoPiece.etage}</p>
                </div>
            </div>

            <div className="voir-plus-grid">
                <CapteursListe capteurs={capteurs} />
                <ObjetsListe objets={infoPiece.objets} />
            </div>

            <div className="voir-plus-horloge">
                <span className="temperature-badge">
                    <span>{meteo.temp} °C</span>
                </span>
                <span className="separateur-horloge">{meteo.heure}</span>
            </div>
        </div>
    );
}