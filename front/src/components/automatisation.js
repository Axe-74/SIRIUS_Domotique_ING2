import React, { useEffect, useState } from 'react';
import { Table, Container, Badge, Spinner } from 'react-bootstrap';
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
        const intervalId = setInterval(chargerDonnees, 5000);// temps de refresh
        return function cleanup() {
            clearInterval(intervalId);
        };
    }, []);

    return (
        <Container className="mt-5">
            <h2 className="mb-4">
                Liste des Automatisations
            </h2>

            <Table striped bordered hover responsive className="shadow-sm">
                <thead className="bg-dark text-white">
                <tr>
                    <th>ID</th>
                    <th>Nom de l'automatisation</th>
                    <th>Statut</th>
                    <th>Seuil de déclenchement</th>
                </tr>
                </thead>
                <tbody>
                {loading ? (
                    <tr>
                        <td colSpan="4" className="text-center">
                            <Spinner animation="border" size="sm" /> Chargement initial...
                        </td>
                    </tr>
                ) : automatisations.length === 0 ? (
                    <tr>
                        <td colSpan="4" className="text-center">Aucune automatisation trouvée.</td>
                    </tr>
                ) : (
                    automatisations.map((auto) => (
                        <tr key={auto.idAutomatisation}>
                            <td>{auto.idAutomatisation}</td>
                            <td>{auto.nom}</td>
                            <td>
                                {auto.etats ? (
                                    <Badge bg="success">Déclenché (ON)</Badge>
                                ) : (
                                    <Badge bg="secondary">Non déclenché (OFF)</Badge>
                                )}
                            </td>
                            <td>{auto.seuil_de_declenchement +" C°" }</td>
                        </tr>
                    ))
                )}
                </tbody>
            </Table>
        </Container>
    );
}