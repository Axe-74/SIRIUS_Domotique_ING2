import React, { useEffect, useState } from 'react';
import {GET_ARRET_SCENARIO_CANICULE, GET_SCENARIO_CANICULE} from "../constants/back";
import axios from "axios";
import '../styles/Scenario.css';


export default function Scenario() {
    const LancementScenario = () => {
        axios.get(GET_SCENARIO_CANICULE).then((response) => {
            alert("Le scénario a été activé avec succès !");
        }).catch(error => {
            alert("Erreur lors de l'activation" + error);
        });
    };

    const StopScenario = () => {
        axios.get(GET_ARRET_SCENARIO_CANICULE).then((response) => {
            alert("Le scénario a été désactivé avec succès !");
        }).catch(error => {
            alert("Erreur lors de la désactivation:" + error);
        });
    };


    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
            <h1>Scénarios diponible</h1>

            <button
                onClick={LancementScenario}
                className="btn-scenario btn-launch"
            >
                Scénario Canicule
            </button>

            <button
                onClick={StopScenario}
                className="btn-scenario btn-stop"
            >
                Stop Scénario Canicule
            </button>
        </div>
    );
}

