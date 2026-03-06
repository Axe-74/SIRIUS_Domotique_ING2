import React from 'react';

export default function MesuresActuelleComponents({ mesure }) {
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
}