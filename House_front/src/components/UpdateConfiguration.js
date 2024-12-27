import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { GET_CONFIG_LOGIN, UPDATE_CONFIGURATION } from '../constants/back';
import { FaSave, FaExclamationCircle } from 'react-icons/fa';
import '../styles/ConfigurationForm.css';

const UpdateConfiguration = () => {
    const { deviceId } = useParams();
    const [config, setConfig] = useState(null);
    const [mode, setMode] = useState('Automatic');
    const [energySaving, setEnergySaving] = useState(false);
    const [schedule, setSchedule] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const navigate = useNavigate();
    const login = localStorage.getItem('username');

    useEffect(() => {
        const fetchConfiguration = async () => {
            try {
                const response = await fetch(GET_CONFIG_LOGIN(login));
                if (!response.ok) {
                    throw new Error('Failed to fetch configurations');
                }
                const data = await response.json();
                const selectedConfig = data.find((item) => item.device.id === parseInt(deviceId));

                if (!selectedConfig) {
                    throw new Error('Configuration not found for this device.');
                }

                setConfig(selectedConfig);
                setMode(selectedConfig.mode);
                setEnergySaving(selectedConfig.energySaving);
                setSchedule(selectedConfig.schedule);
            } catch (err) {
                setError(err.message);
            }
        };

        fetchConfiguration();
    }, [deviceId, login]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        const updatedConfig = {
            configurationName: config.configurationName,
            mode,
            energySaving,
            schedule,
        };

        try {
            const response = await fetch(UPDATE_CONFIGURATION(deviceId), {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(updatedConfig),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.error || 'Failed to update configuration.');
            }

            setSuccess('Configuration updated successfully!');
            setError('');
            setTimeout(() => {
                navigate('/updateConfig');
            }, 1500);
        } catch (err) {
            setError(err.message);
            setSuccess('');
        }
    };

    if (!config) {
        return <p>Loading configuration...</p>;
    }

    return (
        <div className="config-form-container">
            <h1>Update Configuration</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Configuration Name</label>
                    <input type="text" value={config.configurationName} disabled />
                </div>

                <div className="form-group">
                    <label>Mode</label>
                    <select value={mode} onChange={(e) => setMode(e.target.value)}>
                        <option value="Automatic">Automatic</option>
                        <option value="Manual">Manual</option>
                    </select>
                </div>

                <div className="form-group switch-container">
                    <label>Energy Saving</label>
                    <div
                        className={`switch ${energySaving ? 'active' : ''}`}
                        onClick={() => setEnergySaving(!energySaving)}
                    >
                        <div className="slider"></div>
                    </div>
                </div>

                <div className="form-group">
                    <label>Schedule</label>
                    <input
                        type="text"
                        value={schedule}
                        onChange={(e) => setSchedule(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" className="submit-btn">
                    <FaSave /> Update Configuration
                </button>
            </form>

            {error && (
                <div className="error-message">
                    <FaExclamationCircle /> {error}
                </div>
            )}
            {success && (
                <div className="success-message">
                    {success}
                </div>
            )}
        </div>
    );
};

export default UpdateConfiguration;
