import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import '../styles/DetailsTemplates.css';
import axios from "axios";
import { LOCAL_HOST_TEMPLATES } from "../constants/back";

export default function DetailsTemplates() {
    const [templates, setTemplates] = useState([]);
    const navigate = useNavigate();

    const setTemplatesData = async () => {
        try {
            const response = await axios.get(LOCAL_HOST_TEMPLATES);
            const sortedTemplates = response.data.sort((a, b) => {
                return a.idTemplate - b.idTemplate;
            });
            setTemplates(sortedTemplates);
        } catch (error) {
            console.error('Error occurred while loading templates:', error);
        }
    };


    useEffect(() => {
        setTemplatesData();
    }, []);

    const handleTemplateClick = (templateId) => {
        navigate(`/DetailsTemplates/${templateId}`);
    };

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
            <div className="contenttemplate">
                <div className="rectangletemplate">
                    Templates
                    <i className="fa-solid fa-folder-tree"></i>
                </div>
                <div>
                    <section>
                        <table className="tableTemplate">
                            <thead>
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Language</th>
                                <th scope="col">Channel</th>
                                <th scope="col">View</th>
                                <th scope="col">Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            {templates.map((template, index) => (
                                <tr key={index}>
                                    <td>{template.templateName}</td>
                                    <td>{template.language}</td>
                                    <td>{template.channel}</td>
                                    <td>
                                        {template.channel === "CALL" ? (
                                            <span>&#x2573;</span>
                                        ) : (
                                            <Link to={`/DetailsTemplates1/${template.idTemplate}`} state={{ idTemplate: template.idTemplate }}>
                                                &#128065;
                                            </Link>
                                        )}
                                    </td>
                                    <td>
                                        {template.channel === "CALL" ? (
                                            <span>&#x2573;</span>
                                        ) : (
                                            <Link to={`/DetailsTemplates2/${template.idTemplate}`} state={{ idTemplate: template.idTemplate }}>
                                                &#x270E;
                                            </Link>
                                        )}
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
