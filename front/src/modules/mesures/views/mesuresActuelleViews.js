import React, { useEffect, useState } from 'react';
import { getDerniereMesure } from '../api/mesuresActuelleApi';
import MesuresActuelleComponents from '../components/mesuresActuelleComponents';

export default function MesuresActuelleViews() {
    const [mesure, setMesure] = useState(null);

    const recupererDerniereMesure = () => {
        getDerniereMesure()
            .then(data => {
                setMesure(data);
            })
            .catch(error => {
                console.error("Erreur axios :", error);
            });
    };

    useEffect(() => {
        recupererDerniereMesure();
        const interval = setInterval(() => {
            recupererDerniereMesure();
        }, 1000);

        return () => clearInterval(interval);
    }, []);

    // Affichage du chargement tant qu'on n'a pas la donnée
    if (!mesure) return (
        <div className="container text-center mt-5">
            <div className="spinner-border text-primary" role="status"></div>
            <p>En attente de données du capteur...</p>
        </div>
    );

    return (
        <MesuresActuelleComponents mesure={mesure} />
    );
}