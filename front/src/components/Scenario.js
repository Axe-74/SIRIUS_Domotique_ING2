import React, { useEffect, useState } from 'react';
import {GET_ARRET_SCENARIO_CANICULE, GET_SCENARIO_CANICULE} from "../constants/back";
import axios from "axios";


export default function Scenario() {
    const LancementScenario = () => {
        axios.get(GET_SCENARIO_CANICULE).then((response) => {

        }).catch(error => {
            alert("Erreur lors de l'activation" + error);
        });
    };

    const StopScenario = () => {
        axios.get(GET_ARRET_SCENARIO_CANICULE).then((response) => {
        }).catch(error => {
            alert("Erreur lors de la désactivation:" + error);
        });
    };

    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
            <h1>Scénarios diponible</h1>

            <button
                onClick={LancementScenario}
                style={{ padding: '10px 20px', cursor: 'pointer' }}
            >
                Scénario Canicule
            </button>

            <button
                onClick={StopScenario}
                style={{ padding: '10px 20px', cursor: 'pointer' }}
            >
                Stop Scénario Canicule
            </button>
        </div>
    );
}

