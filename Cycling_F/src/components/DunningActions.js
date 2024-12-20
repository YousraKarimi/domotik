import React, {useEffect, useState} from "react";
import '../styles/DunningActions.css';
import axios from "axios";
import { LOCAL_HOST_ACTIONS} from "../constants/back";
import '@fortawesome/fontawesome-free/css/all.min.css';
import {Link} from "react-router-dom";
export default function DunningActions() {
    const [Actions, setActions] = useState([]);

    const setActionsData = async () => {
        try {
            const response = await axios.get(LOCAL_HOST_ACTIONS);
            setActions(response.data);
        } catch (error) {
            console.error('Error occurred while loading Levels:', error);
        }
    };
    useEffect(() => {
        setActionsData()
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
            <div className="contentactions">
                <div className="rectangleactions">
                    Actions
                    <i className="fa-solid fa-arrows-to-dot"></i>
                </div>
                <div className="">
                    <section>
                        <table className="tableactions">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Channel</th>


                            </tr>
                            </thead>
                            <tbody>
                            {Actions.map((DunningAction, index) => (
                                <tr key={index}>
                                    <td>{DunningAction.actionName}</td>
                                    <td>{DunningAction.channel}</td>


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

