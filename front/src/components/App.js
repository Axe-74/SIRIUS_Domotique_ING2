import React from 'react';
import logo from '../assets/logo.jpeg';
import '../styles/App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import smartHome from '../assets/smart-home.jpg';


export default function App() {
  return (
    <div className="App">
        <img src={logo} alt="logo" className="logo" />
        <img src={smartHome} alt="smarthome" className="smarthome" />
        <textarea
            value="Bienvenue dans votre application de domotique !"
            readOnly
            className="readonly-text"
        />
    </div>
  );
}
