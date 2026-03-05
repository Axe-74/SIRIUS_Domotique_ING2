import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import NotFound from "./NotFound";
import MesureTempActuelle from "./MesureTempActuelle";
import Automatisation from "./Automatisation";
import Maison from "./Maison";
import Scenario from "./Scenario";
import SimulationJournee from "./SimulationJournee";
import Historique from "./Historique";


export default function Router () {
    return (
        <BrowserRouter>
            <div>
                <Navbar />
                <Routes>
                    <Route path="/" element={<App />}/>
                    <Route path="/mesures_temp" element={<MesureTempActuelle />}/>
                    <Route path="/automatisation" element={<Automatisation />}/>
                    <Route path="/simulation_journee" element={<SimulationJournee />}/>
                    <Route path="/maison" element={<Maison />} />
                    <Route path="/historique" element={<Historique />} />
                    <Route path="/scenario" element={<Scenario />} />
                    <Route path="*" element={<NotFound />}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
};