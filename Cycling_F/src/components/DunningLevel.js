import React, { useEffect, useState } from "react";
import '../styles/DunningLevels.css';
import axios from "axios";
import { LOCAL_HOST_LEVELS } from "../constants/back";
import '@fortawesome/fontawesome-free/css/all.min.css';
import {Link} from "react-router-dom";

export default function DunningLevel() {
    const [Levels, setLevels] = useState([]);

    const setLevelsData = async () => {
        try {
            const response = await axios.get(LOCAL_HOST_LEVELS);
            setLevels(response.data);
        } catch (error) {
            console.error('Error occurred while loading Levels:', error);
        }
    };

    useEffect(() => {
        setLevelsData();
    }, []);

    return (
        <div>
            <div className="navbar1">
                <ul className="ul1">
                    <li><Link to="/DunningSubscription">Debts</Link></li>
                    <li><Link to="/DetailsTemplates">Templates</Link></li>
                    <li><Link to="/DunningLevel">Levels</Link></li>
                    <li><Link to="/DunningActions">Actions</Link></li>
                    <li><Link to="/DunningDocumentsC">Settings</Link></li>
                </ul>
            </div>
            <div className="contentlevel">
                <div className="rectanglelevel">
                    Levels
                    <i className="fas fa-stream"></i>
                </div>
                <div className="">
                    <section>
                        <table className="tablelevels">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">days overdue (j+)</th>
                                <th scope="col">state</th>
                            </tr>
                            </thead>
                            <tbody>
                            {Levels.map((DunningLevel, index) => (
                                <tr key={index}>
                                    <td>{DunningLevel.levelName}</td>
                                    <td>{DunningLevel.daysOverdue}</td>
                                    <td>
                                            <span>
                                                {DunningLevel.levelState ?
                                                    <span>  <i className="fas fa-check"></i></span> :
                                                    <span>  <i className="fas fa-times"></i></span>
                                                }
                                            </span>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </section>
                </div>
            </div>
        </div>
    );
}
