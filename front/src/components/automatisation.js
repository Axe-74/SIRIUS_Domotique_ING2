import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Container, Badge } from 'react-bootstrap';
import { GET_AUTOMATISATION } from "../constants/back"; // Assure-toi que ce chemin est bon

export default function Automatisation() {
    // 1. J'utilise 'automatisations' (minuscule) pour la liste, c'est plus propre
    const [automatisations, setAutomatisations] = useState([]);

    // 2. Fonction pour récupérer les données
    const fetchAutomatisations = async () => {
        try {
            const response = await axios.get(GET_AUTOMATISATION);
            setAutomatisations(response.data);
        } catch (error) {
            alert("Erreur lors du chargement : " + error);
            console.error(error);
        }
    }

    // 3. IMPORTANT : useEffect déclenche la récupération au chargement de la page
    useEffect(() => {
        fetchAutomatisations();
    }, []); // Le tableau vide [] assure que ça ne tourne qu'une seule fois

    return (
        <Container className="mt-5">
            <h2 className="mb-4">Liste des Automatisations</h2>

            <Table striped bordered hover responsive className="shadow-sm">
                <thead className="bg-dark text-white">
                <tr>
                    <th>#ID</th>
                    <th>Nom de l'automatisation</th>
                    <th>État actuel</th>
                </tr>
                </thead>
                <tbody>
                {/* 4. Ici j'utilise bien la variable déclarée en haut 'automatisations' */}
                {automatisations.map((auto) => (
                    <tr key={auto.idAutomatisation}>
                        <td>{auto.idAutomatisation}</td>
                        <td>{auto.nom}</td>
                        <td>
                            {auto.etats ? (
                                <Badge bg="success">Actif (ON)</Badge>
                            ) : (
                                <Badge bg="secondary">Inactif (OFF)</Badge>
                            )}
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </Container>
    );
}