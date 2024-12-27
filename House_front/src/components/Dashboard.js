import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css';
import { FaPlus, FaEye, FaWrench, FaTabletAlt, FaBell } from 'react-icons/fa';

const Dashboard = () => {
    let navigate = useNavigate();

    return (
        <div className="dashboard-container">

            <button className="notification-button" onClick={() => navigate('/Notifications')}>
                <FaBell />
            </button>

            <div className="button-grid">
                <button onClick={() => navigate('/addConfig')} className="dashboard-button">
                    <FaPlus size={50} />
                    <span>Add Configuration</span>
                </button>
                <button onClick={() => navigate('/viewConfigs')} className="dashboard-button">
                    <FaEye size={50} />
                    <span>See Configurations</span>
                </button>
                <button onClick={() => navigate('/myDevices')} className="dashboard-button">
                    <FaTabletAlt size={50} />
                    <span>My Devices</span>
                </button>
                <button onClick={() => navigate('/updateConfig')} className="dashboard-button">
                    <FaWrench size={50} />
                    <span>Update Configuration</span>
                </button>
            </div>
        </div>
    );
};

export default Dashboard;
