import React, { useEffect, useState } from 'react';
import { fetchAutomatisations } from "../api/automatisationApi";
import AutomatisationTable from "../components/Tableau";
import '../styles/Automatisation.css';

export default function AutomatisationPage() {
    const [automatisations, setAutomatisations] = useState([]);

    const refreshData = () => {
        fetchAutomatisations()
            .then(data => setAutomatisations(data))
            .catch(error => alert("Erreur lors de la récupération : " + error));
    };

    useEffect(() => {
        refreshData();
        const intervalId = setInterval(refreshData, 5000);
        return () => clearInterval(intervalId);
    }, []);

    return (
        <div className="automatisation-container">
            <h2>Tableau des Automatisations</h2>
            <AutomatisationTable data={automatisations} />
        </div>
    );
}