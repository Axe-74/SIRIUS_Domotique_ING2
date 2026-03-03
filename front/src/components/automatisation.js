import React, { useEffect, useState } from 'react';
import { GET_AUTOMATISATION } from "../constants/back";
import axios from "axios";
import '../styles/Automatisation.css';

export default function Automatisation() {
    const [automatisations, setAutomatisations] = useState([]);

    const recuperationDonnees = () => {
        axios.get(GET_AUTOMATISATION)
            .then((response) => {
                setAutomatisations(response.data);
            })
            .catch(error => {
                alert("Erreur lors de la récupération : " + error);
            });
    };

    useEffect(() => {
        recuperationDonnees();
    }, []);


    return (
        <div className="automatisation-container">
            <h2>Tableau des Automatisations</h2>
            <table className="custom-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Seuil</th>
                    <th>Objets Reliés</th>
                    <th>Etat</th>
                </tr>
                </thead>
                <tbody>
                {automatisations.map((item) => (
                    <tr key={item.idAutomatisation}>
                        <td className="id-column">{item.idAutomatisation}</td>
                        <td >{item.nom}</td>
                        <td>
                            <span className="temp-badge">{item.seuilDeDeclenchement} °C</span>
                        </td>
                        <td className="objects-list">
                            {item.objetsRelies.map(obj => obj.nom_objet).join(', ')}
                        </td>
                        <td>{item.etats ? "Actif" : "Inactif"}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}