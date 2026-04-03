import React from 'react';

export default function Tableau({ data }) {
    if (!data) {
        return <p>Chargement des données...</p>;
    }

    return (
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
            {data.map((item) => (
                <tr key={item.idAutomatisation}>
                    <td className="id-column">{item.idAutomatisation}</td>
                    <td>{item.nom}</td>
                    <td>
                        <span className="temp-badge">{item.seuilDeDeclenchement} °C</span>
                    </td>
                    <td className="objects-list">
                        {item.objetsRelies && item.objetsRelies.length > 0
                            ? item.objetsRelies.map(obj => obj.nom_objet).join(', ')
                            : "Aucun objet"}
                    </td>
                    <td>{item.etats ? "Actif" : "Inactif"}</td>
                </tr>
            ))}
            </tbody>
        </table>
    );
}