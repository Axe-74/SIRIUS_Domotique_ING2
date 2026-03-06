import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import NotFound from "./NotFound";
import Automatisation from "./Automatisation";
import Maison from "./Maison";
import Scenario from "./Scenario";
import SimulationViews from "../modules/simulation/views/simulationViews";
import MesuresHistoriqueViews from "../modules/mesures/views/mesuresHistoriqueViews";
import MesuresActuelleViews from "../modules/mesures/views/mesuresActuelleViews";


export default function Router () {
    return (
        <BrowserRouter>
            <div>
                <Navbar />
                <Routes>
                    <Route path="/" element={<App />}/>
                    <Route path="/automatisation" element={<Automatisation />}/>
                    <Route path="/simulation_journee" element={<SimulationViews />}/>
                    <Route path="/maison" element={<Maison />} />
                    <Route path="/historique" element={<MesuresHistoriqueViews />} />
                    <Route path="/mesures_temp" element={<MesuresActuelleViews />}/>
                    <Route path="/scenario" element={<Scenario />} />
                    <Route path="*" element={<NotFound />}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
};