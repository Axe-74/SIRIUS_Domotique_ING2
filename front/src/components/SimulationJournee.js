import React from 'react';
import axios from "axios";
import '../styles/SimulationJournee.css';
import { POST_SIMULATION_ACCELEREE } from "../constants/back";


export default function SimulationJournee() {

    const LancementSimulation = () => {
        axios.post(POST_SIMULATION_ACCELEREE).then((response) => {
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
