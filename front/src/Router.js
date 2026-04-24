import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./components/App";
import Navbar from "./Navbar";
import NotFound from "./components/NotFound";
import MaisonView from "./modules/maison/views/MaisonView";
import VoirPlus from "./modules/maison/views/VoirPlus";
import AutomatisationPage from "./modules/Automatisation/views/AutomatisationPage";
import ScenarioPage from "./modules/Scenario/views/ScenarioPage";
import SimulationViews from "./modules/simulation/views/simulationViews";
import MesuresHistoriqueViews from "./modules/mesures/views/mesuresHistoriqueViews";
import MesuresActuelleViews from "./modules/mesures/views/mesuresActuelleViews";


export default function Router () {
    return (
        <BrowserRouter>
            <div>
                <Navbar />
                <Routes>
                    <Route path="/" element={<App />}/>
                    <Route path="/automatisation" element={<AutomatisationPage />}/>
                    <Route path="/simulation_journee" element={<SimulationViews />}/>
                    <Route path="/maison" element={<MaisonView />} />
                    <Route path="/piece/:id" element={<VoirPlus />} />
                    <Route path="/scenario" element={<ScenarioPage />} />
                    <Route path="/historique" element={<MesuresHistoriqueViews />} />
                    <Route path="/mesures_temp" element={<MesuresActuelleViews />}/>
                    <Route path="*" element={<NotFound />}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
};