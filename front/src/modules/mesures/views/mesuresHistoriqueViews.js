import React, { useEffect, useState } from 'react';
import MesuresHistoriqueComponents from '../components/mesuresHistoriqueComponents';
import '../../simulation/styles/SimulationJournee.css';
import axios from "axios";
import {GET_HISTORIQUE} from "../../../constants/back";

export default function MesuresHistoriqueViews() {

    const [donneesGraphique, setDonneesGraphique] = useState([]);

    const idCapteurExt = 2;
    const idCapteurInt = 11;
    const idCapteurMouv = 3;

    const recupererHistorique = () => {
        Promise.all([
            axios.get(GET_HISTORIQUE + `?idCapteur=${idCapteurExt}`),
            axios.get(GET_HISTORIQUE + `?idCapteur=${idCapteurInt}`),
            axios.get(GET_HISTORIQUE + `?idCapteur=${idCapteurMouv}`)
        ])
            .then(([reponseExt, reponseInt, reponseMouv]) => {
                const donneesPropres = reponseExt.data.map((mesureExt, index) => {
                    const mesureInt = reponseInt.data[index];
                    const mesureMouv = reponseMouv.data[index];
                    const date = new Date(mesureExt.date);

                return {
                    valeurExt: mesureExt.valeur,
                    valeurInt: mesureInt ? mesureInt.valeur : null,
                    valeurMouv: mesureMouv ? mesureMouv.valeur : null,
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
        <div className="container mt-5">
            <MesuresHistoriqueComponents data={donneesGraphique} />
        </div>
    );
}