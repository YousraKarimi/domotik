import React from 'react';
import '../styles/App.css';
import House_Config from '../assets/House_Config.png';


export default function App() {
    return (
        <div className="countainer">

            <header class="header">
                <img src={House_Config} alt="House_Config" className="House_Config"></img>
            </header>

        </div>

    );
}
