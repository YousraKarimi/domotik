import React, { useEffect, useState } from 'react';
import { fetchNotifications } from '../constants/back';
import { useNavigate } from 'react-router-dom';
import '../styles/Notifications.css';

const Notifications = () => {
    const [notifications, setNotifications] = useState([]);
    const userId = sessionStorage.getItem('userId');
    let navigate = useNavigate();

    useEffect(() => {
        const getNotifications = async () => {
            if (!userId) {
                console.warn('No user ID found in session');
                return;
            }
            console.log("User ID from session:", userId);

            try {
                const data = await fetchNotifications(userId);
                console.log('Raw Notifications from API:', data);

                setNotifications(data);
            } catch (error) {
                console.error('Failed to fetch notifications:', error);
            }
        };

        getNotifications();
    }, [userId]);

    return (
        <div className="notifications-container">
            <h1>Notifications</h1>

            {notifications && notifications.length > 0 ? (
                <ul className="notification-list">
                    {notifications.map((message, index) => (
                        <li key={index} className="notification-item">
                            <strong>{message}</strong>
                        </li>
                    ))}
                </ul>
            ) : (
                <p className="no-notifications">No notifications available</p>
            )}

            <button className="back-button" onClick={() => navigate('/dashboard')}>
                Back to Dashboard
            </button>
        </div>
    );
};

export default Notifications;

