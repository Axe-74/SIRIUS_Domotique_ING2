import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import NotFound from "./NotFound";
import MesureTempActuelle from "./MesureTempActuelle";
import AutomatisationPage from "../modules/Automatisation/views/AutomatisationPage";
import ScenarioPage from "../modules/Scenario/views/ScenarioPage";
import Automatisation from "./Automatisation";
import Maison from "./Maison";
import SimulationJournee from "./SimulationJournee";
import Historique from "./Historique";
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
                    <Route path="/mesures_temp" element={<MesureTempActuelle />}/>
                    <Route path="/automatisation" element={<AutomatisationPage />}/>
                    <Route path="/simulation_journee" element={<SimulationJournee />}/>
                    <Route path="/automatisation" element={<Automatisation />}/>
                    <Route path="/simulation_journee" element={<SimulationViews />}/>
                    <Route path="/maison" element={<Maison />} />
                    <Route path="/scenario" element={<ScenarioPage />} />
                    <Route path="/historique" element={<Historique />} />
                    <Route path="/scenario" element={<ScenarioPage />} />
                    <Route path="/historique" element={<MesuresHistoriqueViews />} />
                    <Route path="/mesures_temp" element={<MesuresActuelleViews />}/>
                    <Route path="/scenario" element={<Scenario />} />
                    <Route path="*" element={<NotFound />}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
};