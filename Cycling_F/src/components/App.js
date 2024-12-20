import React from 'react';
import '../styles/App.css';
import cyclingImage from '../assets/cycling.png';


export default function App() {
    return (
        <div className="countainer">

            <header class="header">
                <img src={cyclingImage} alt="cyclingImage" className="logo"></img>
            </header>

        </div>

    );
}
