import React, { useEffect, useState } from 'react';
import { GET_CONFIG_LOGIN } from '../constants/back';
import '../styles/ConfigurationList.css';
import { FaCogs, FaExclamationCircle } from 'react-icons/fa';

const ConfigurationList = () => {
    const [configurations, setConfigurations] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    const fullName = localStorage.getItem('username');

    useEffect(() => {
        const fetchConfigurations = async () => {
            if (!fullName) {
                setError('User full name not provided');
                setLoading(false);
                return;
            }

            try {
                const response = await fetch(GET_CONFIG_LOGIN(fullName));

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.error || 'Failed to fetch configurations');
                }

                const data = await response.json();
                setConfigurations(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchConfigurations();
    }, [fullName]);

    return (
        <div className="config-list-container">
            <h1>My Configurations</h1>

            {loading && <p>Loading configurations...</p>}

            {error && (
                <div className="error-message">
                    <FaExclamationCircle /> {error}
                </div>
            )}

            {!loading && configurations.length === 0 && !error && (
                <p>No configurations found for user <strong>{fullName}</strong>.</p>
            )}

            <div className="config-card-grid">
                {configurations.map((config) => (
                    <div className="config-card" key={config.id}>
                        <div className="icon-container">
                            <FaCogs size={50} />
                        </div>
                        <div className="config-details">
                            <h3>{config.configurationName}</h3>
                            <p><strong>Energy Saving:</strong> {config.energySaving ? 'Yes' : 'No'}</p>
                            <p><strong>Mode:</strong> {config.mode}</p>
                            <p><strong>Schedule:</strong> {config.schedule}</p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ConfigurationList;
