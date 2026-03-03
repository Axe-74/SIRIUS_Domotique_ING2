import React from 'react';
import axios from "axios";
import '../styles/SimulationJournee.css';

export default function Simulation_Journee() {

    const LancementSimulation = () => {
        axios.post("http://localhost:8080/api/simulation_acceleree/journee").then((response) => {
            alert("Simulation de la journée lancée");
        }).catch(error => {
            alert(error);
        });
    };

    return (
        <div className="simulation-container">
            <h1>Simulation d'une journée de mesures en 2 minutes</h1>
            <h1>↓</h1>


            <button
                onClick={LancementSimulation}
                className="btn-simulation"
            >
                Lancer la Simulation
            </button>
        </div>
    );
}