import React from 'react';
import {Link} from "react-router-dom";

export default function Navbar(){
    return (
        <ul className="nav justify-content-center my-3 monNavbar">
            <li className="nav-item">
                <Link className="nav-link" to="/login">Login</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/map">Map</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/station">Station</Link>
            </li>
            <li className="nav-item">
                <Link className="nav-link" to="/BikeTable">Bike Table</Link>
            </li>
        </ul>
    );
};