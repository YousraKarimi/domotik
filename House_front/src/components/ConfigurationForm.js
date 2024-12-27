import React, { useEffect, useState } from 'react';
import { GET_DEVICES_LOGIN, ADD_CONFIGURATION } from '../constants/back';
import { FaSave, FaExclamationCircle } from 'react-icons/fa';
import '../styles/ConfigurationForm.css';

const ConfigurationForm = () => {
    const [devices, setDevices] = useState([]);
    const [selectedDevice, setSelectedDevice] = useState('');
    const [configName, setConfigName] = useState('');
    const [mode, setMode] = useState('Automatic');
    const [energySaving, setEnergySaving] = useState(false);
    const [schedule, setSchedule] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const login = localStorage.getItem('username');

    useEffect(() => {
        const fetchDevices = async () => {
            try {
                const response = await fetch(GET_DEVICES_LOGIN(login));
                const data = await response.json();
                setDevices(data);
            } catch (err) {
                setError('Failed to fetch devices.');
            }
        };

        fetchDevices();
    }, [login]);

    // Auto-hide error after 3 seconds
    useEffect(() => {
        if (error) {
            const timer = setTimeout(() => {
                setError('');
            }, 3000);
            return () => clearTimeout(timer);
        }
    }, [error]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedDevice || !configName || !schedule) {
            setError('All fields are required.');
            return;
        }

        const configuration = {
            configurationName: configName,
            mode,
            energySaving,
            schedule
        };

        try {
            const response = await fetch(ADD_CONFIGURATION(selectedDevice), {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(configuration)
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.error || 'Failed to add configuration.');
            }

            const data = await response.json();
            setSuccess('Configuration added successfully!');
            setError('');
            setConfigName('');
            setSchedule('');
            setSelectedDevice('');
            setEnergySaving(false);
            setMode('Automatic');
        } catch (err) {
            setError(err.message);
            setSuccess('');
        }
    };

    return (
        <div className="config-form-container">
            <h1>Add Configuration</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Device</label>
                    <select
                        value={selectedDevice}
                        onChange={(e) => setSelectedDevice(e.target.value)}
                        required
                    >
                        <option value="">Select Device</option>
                        {devices.map((device) => (
                            <option key={device.id} value={device.id}>
                                {device.name}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="form-group">
                    <label>Configuration Name</label>
                    <input
                        type="text"
                        value={configName}
                        onChange={(e) => setConfigName(e.target.value)}
                        placeholder="Enter configuration name"
                        required
                    />
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
                        placeholder="Enter schedule (e.g. 10:00 AM - 6:00 PM)"
                        required
                    />
                </div>

                <button type="submit" className="submit-btn">
                    <FaSave /> Add Configuration
                </button>
            </form>

            {error && (
                <div className="popup-error">
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

export default ConfigurationForm;
