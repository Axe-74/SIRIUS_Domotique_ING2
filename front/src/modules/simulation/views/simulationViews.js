import React from 'react';
import { lancerSimulationAcceleree } from '../api/simulationApi';
import Button from '../../../commons/components/Bouton';
import '../styles/SimulationJournee.css';

export default function SimulationViews() {

    const lancerLancementSimulation = () => {
        lancerSimulationAcceleree()
            .then(() => {
                alert("Simulation de la journée lancée");
            })
            .catch(error => {
                alert("Erreur de simulation : " + error);
            });
    };

    return (
        <div className="simulation-container">
            <h1>Simulation d'une journée de mesures en 2 minutes</h1>
            <h1>↓</h1>

            <Button
                onClick={lancerLancementSimulation}
                className="btn-simulation"
            >
                Lancer la Simulation
            </Button>
        </div>
    );
}