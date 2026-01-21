import React, { useEffect, useState } from 'react';
import { GET_AUTOMATISATION } from "../constants/back";

export default function Automatisation() {
    const [automatisations, setAutomatisations] = useState([]);
    const [loading, setLoading] = useState(true);

    const chargerDonnees = () => {
        fetch(GET_AUTOMATISATION)
            .then(function(reponse) {
                if (!reponse.ok) {
                    throw new Error("Erreur HTTP " + reponse.status);
                }
                return reponse.json();
            })
            .then(function(donnees) {
                setAutomatisations(donnees);
                setLoading(false);
            })
            .catch(function(erreur) {
                console.error("Erreur fetch :", erreur);
            });
    };

    useEffect(function() {
        chargerDonnees();
        const intervalId = setInterval(chargerDonnees, 5000); // temps de refresh
        return function cleanup() {
            clearInterval(intervalId);
        };
    }, []);

    return (
        <div style={styles.container}>
            <h2 style={styles.title}>Liste des Automatisations</h2>

            <div style={styles.tableWrapper}>
                <table style={styles.table}>
                    <thead style={styles.thead}>
                    <tr>
                        <th style={styles.th}>ID</th>
                        <th style={styles.th}>Nom de l'automatisation</th>
                        <th style={styles.th}>Statut</th>
                        <th style={styles.th}>Seuil de déclenchement</th>
                    </tr>
                    </thead>
                    <tbody>
                    {loading ? (
                        <tr>
                            <td colSpan="4" style={styles.tdCenter}>
                                Chargement des données
                            </td>
                        </tr>
                    ) : automatisations.length === 0 ? (
                        <tr>
                            <td colSpan="4" style={styles.tdCenter}>
                                Aucune automatisation trouvée.
                            </td>
                        </tr>
                    ) : (
                        automatisations.map((auto, index) => (
                            <tr key={auto.idAutomatisation} style={index % 2 === 0 ? styles.trEven : styles.trOdd}>
                                <td style={styles.td}>{auto.idAutomatisation}</td>
                                <td style={styles.td}>{auto.nom}</td>
                                <td style={styles.td}>
                                    {}
                                    {auto.etats ? (
                                        <span style={styles.badgeSuccess}>Déclenché (ON)</span>
                                    ) : (
                                        <span style={styles.badgeSecondary}>Non déclenché (OFF)</span>
                                    )}
                                </td>
                                <td style={styles.td}>
                                    {}
                                    {auto.seuilDeDeclenchement !== null
                                        ? auto.seuilDeDeclenchement + " °C"
                                        : "N/A"}
                                </td>
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
const styles = {
    container: {
        maxWidth: '900px',
        margin: '40px auto',
        fontFamily: 'Arial, sans-serif',
        padding: '20px',
    },
    title: {
        marginBottom: '20px',
        color: '#333',
        textAlign: 'center'
    },
    tableWrapper: {
        boxShadow: '0 4px 8px rgba(0,0,0,0.1)',
        borderRadius: '8px',
        overflow: 'hidden',
        border: '1px solid #ddd'
    },
    table: {
        width: '100%',
        borderCollapse: 'collapse',
    },
    thead: {
        backgroundColor: '#343a40',
        color: '#ffffff',
    },
    th: {
        padding: '12px 15px',
        textAlign: 'left',
        fontWeight: 'bold',
        borderBottom: '1px solid #ddd'
    },
    td: {
        padding: '12px 15px',
        borderBottom: '1px solid #ddd',
        color: '#555'
    },
    tdCenter: {
        padding: '20px',
        textAlign: 'center',
        fontStyle: 'italic',
        color: '#777'
    },
    trEven: {
        backgroundColor: '#ffffff',
    },
    trOdd: {
        backgroundColor: '#f8f9fa',
    },

    badgeSuccess: {
        backgroundColor: '#28a745',
        color: 'white',
        padding: '5px 10px',
        borderRadius: '20px',
        fontSize: '0.85em',
        fontWeight: 'bold',
        display: 'inline-block'
    },
    badgeSecondary: {
        backgroundColor: '#6c757d',
        color: 'white',
        padding: '5px 10px',
        borderRadius: '20px',
        fontSize: '0.85em',
        fontWeight: 'bold',
        display: 'inline-block'
    }
};
