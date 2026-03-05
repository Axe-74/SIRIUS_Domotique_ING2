import {LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip} from 'recharts';

import React, {useEffect, useState} from 'react';
import axios from "axios";
import '../styles/SimulationJournee.css';
import {GET_HISTORIQUE} from "../constants/back";

export default function Historique() {

    const [donneesGraphique, setDonneesGraphique] = useState([]);

    const idCapteur = 2;

    const recupererHistorique = () => {
        axios.get(GET_HISTORIQUE + `?idCapteur=${idCapteur}`).then(reponseHistorique => {
            const donneesPropres = reponseHistorique.data.map(mesure => {
                const date = new Date(mesure.date);

                return {
                    valeur: mesure.valeur,
                    date: date.toLocaleString('fr-FR', {
                        day: '2-digit',
                        month: '2-digit',
                        hour: '2-digit',
                        minute: '2-digit',
                    })
                };
            })

            setDonneesGraphique(donneesPropres);
        })
            .catch(error => {
                console.error("Erreur lors de la récupération");
                console.error(error);
            });
    }

    useEffect(() => {
        recupererHistorique();
        const minuteur = setInterval(() => {
            recupererHistorique();
        }, 2000);
        return () => {
            clearInterval(minuteur);
        };
    },[])

    return (
        <LineChart
            width={1100}
            height={500}
            data={donneesGraphique}
            margin={{ top: 50, right: 10, left: 100, bottom: 5 }}
        >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="date" minTickGap={30}/>
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="valeur" stroke="#8884d8" dot={false}/>
        </LineChart>
    );
}