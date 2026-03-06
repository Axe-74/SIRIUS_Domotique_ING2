import React from 'react';
import { activateCanicule, stopCanicule } from "../api/scenarioApi";
import ScenarioControls from "../components/Bouton";
import '../styles/Scenario.css';

export default function ScenarioPage() {

    const handleLaunch = async () => {
        try {
            await activateCanicule();
            alert("Le scénario a été activé avec succès !");
        } catch (error) {
            alert("Erreur lors de l'activation : " + error);
        }
    };

    const handleStop = async () => {
        try {
            await stopCanicule();
            alert("Le scénario a été désactivé avec succès !");
        } catch (error) {
            alert("Erreur lors de la désactivation : " + error);
        }
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
            <h1>Scénarios disponibles</h1>
            <ScenarioControls
                onLaunch={handleLaunch}
                onStop={handleStop}
            />
        </div>
    );
}