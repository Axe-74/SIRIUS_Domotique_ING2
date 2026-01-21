import React, { useEffect, useState } from 'react';
import axios from "axios";
import { GET_LAST_MESURE } from "../constants/back";

export default function Mesure_Temp_Actuelle() {
  const [mesure, setMesure] = useState(null);

  const recupererDerniereMesure = () => {
    axios.get(GET_LAST_MESURE)
      .then((response) => {
          if (Array.isArray(response.data) && response.data.length > 0) {
            setMesure(response.data[0]); //premier élément pris pour l'instant, à changer si plusieurs capteurs créés
          } else {
            console.log("La liste reçue est vide");
          }
      })
      .catch((error) => {
          console.error("Erreur axios :", error);
      });
  };

  useEffect(() => {
    recupererDerniereMesure();
    const interval = setInterval(() => {
        recupererDerniereMesure();
    }, 1000);
    return () => clearInterval(interval); //pour que le process cesse quand changement de page
  }, []);


  // Affichage chargement
  if (!mesure) return (
    <div className="container text-center mt-5">
        <div className="spinner-border text-primary" role="status"></div>
        <p>En attente de données du capteur...</p>
    </div>
  );


  // Affichage principal
  return (
      <div className="container text-center mt-5">
        <div className="card shadow">
            <div className="card-header bg-primary text-white">
                <h3>Capteur en Temps Réel</h3>
            </div>
            <div className="card-body">
                <h1 className="display-1 fw-bold text-primary">
                    {mesure.valeur} <span style={{fontSize: "0.7em"}}>°C</span>
                </h1>

                <hr/>

                <div className="row text-muted">
                    <div className="col">
                        <strong>Date :</strong> {new Date(mesure.date).toLocaleString('fr-FR', {
                            day: 'numeric',
                            month: 'numeric',
                            year: 'numeric',
                            hour: '2-digit',
                            minute: '2-digit',
                            second: '2-digit'
                        })}
                    </div>
                    <div className="col">
                        <strong>ID Capteur :</strong> {mesure.id_capteur}
                    </div>
                </div>
            </div>
        </div>
    </div>
  );
};