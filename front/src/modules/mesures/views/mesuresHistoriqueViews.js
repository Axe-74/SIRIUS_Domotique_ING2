import React, { useEffect, useState } from 'react';
import MesuresHistoriqueComponents from '../components/mesuresHistoriqueComponents';
import '../../simulation/styles/SimulationJournee.css';
import axios from "axios";
import {GET_HISTORIQUE} from "../../../constants/back";

export default function MesuresHistoriqueViews() {

    const [donneesGraphique, setDonneesGraphique] = useState([]);

    const idCapteur = 2;

    const recupererHistorique = () => {
        axios.get(GET_HISTORIQUE + `?idCapteur=${idCapteur}`).then(reponseHistorique => {
            const donneesPropres = reponseHistorique.data.map(mesure => {
                const date = new Date(mesure.date);

                return {
                    valeur: mesure.valeur,
                    date: date.toLocaleString('fr-FR', {
                        hour: '2-digit',
                        minute: '2-digit',
                    })
                };
            })

            setDonneesGraphique(donneesPropres);
        })
            .catch(error => {
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
    }, []);

    return (
        <div>
            <MesuresHistoriqueComponents data={donneesGraphique} />
        </div>
    );
}