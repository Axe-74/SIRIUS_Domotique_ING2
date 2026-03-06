import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import NotFound from "./NotFound";
import MesureTempActuelle from "./MesureTempActuelle";
import AutomatisationPage from "../modules/Automatisation/views/AutomatisationPage";
import ScenarioPage from "../modules/Scenario/views/ScenarioPage";
import Maison from "./Maison";
import SimulationJournee from "./SimulationJournee";


export default function Router () {
    return (
        <BrowserRouter>
            <div>
                <Navbar />
                <Routes>
                    <Route path="/" element={<App />}/>
                    <Route path="/mesures_temp" element={<MesureTempActuelle />}/>
                    <Route path="/automatisation" element={<AutomatisationPage />}/>
                    <Route path="/simulation_journee" element={<SimulationJournee />}/>
                    <Route path="/maison" element={<Maison />} />
                    <Route path="/scenario" element={<ScenarioPage />} />
                    <Route path="*" element={<NotFound />}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
};