import React from 'react';
import {Link} from "react-router-dom";

export default function Navbar(){
    return (
            <ul className="nav justify-content-center my-3">
                <li className="nav-item">
                    <Link className="nav-link" to="/">Home</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/mesures_temp">Mesures</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/maison">Maison</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/automatisation">Automatisation</Link>
                </li>
             </ul>
);
};
