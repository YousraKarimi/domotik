import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { GET_DEVICES_LOGIN } from '../constants/back';
import '../styles/DeviceList.css';
import { FaExclamationCircle } from 'react-icons/fa';

const DeviceList = () => {
    const [devices, setDevices] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);

    const navigate = useNavigate();
    const login = localStorage.getItem('username');

    useEffect(() => {
        const fetchDevices = async () => {
            if (!login) {
                setError('User login not provided');
                setLoading(false);
                return;
            }

            try {
                const response = await fetch(GET_DEVICES_LOGIN(login));

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.error || 'Failed to fetch devices');
                }

                const data = await response.json();
                setDevices(data);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchDevices();
    }, [login]);

    return (
        <div className="device-list-container">
            <h2>My Devices</h2>

            {loading && <p>Loading devices...</p>}

            {error && (
                <div className="error-message">
                    <FaExclamationCircle /> {error}
                </div>
            )}

            {!loading && devices.length === 0 && !error && (
                <p>No devices found for user <strong>{login}</strong>.</p>
            )}

            <table className="device-table">
                <thead>
                <tr>
                    <th>Device Name</th>
                    <th>Type</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {devices.map((device) => (
                    <tr key={device.id}>
                        <td>{device.name}</td>
                        <td>{device.type}</td>
                        <td>{device.status}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default DeviceList;
