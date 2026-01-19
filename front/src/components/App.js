import React from 'react';
import logo from '../assets/logo.jpeg';
import '../styles/App.css';
import 'bootstrap/dist/css/bootstrap.min.css';


export default function App() {
  return (
    <div className="App">
        <img src={logo} alt="logo" className="logo" />
        <textarea
            value="Hello world"
            readOnly
            className="readonly-text"
        />
    </div>
  );
}
