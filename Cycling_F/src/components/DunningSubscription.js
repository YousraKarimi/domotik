import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { LOCAL_HOST_INVOICEUNPAID } from '../constants/back';
import '../styles/Dashboard.css';
import '../styles/DunningSubscription.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

export default function DunningSubscription() {
    const [subscriptions, setSubscriptions] = useState([]);
    let navigate = useNavigate();

    const setSubscriptionsData = async () => {
        try {
            const response = await axios.get(LOCAL_HOST_INVOICEUNPAID);
            setSubscriptions(response.data);
        } catch (error) {
            console.error('Error occurred while loading subscriptions:', error);
        }
    };

    useEffect(() => {
        setSubscriptionsData();
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('username');
        navigate('/login');
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
                <button onClick={handleLogout} className="logout-buttonD">Logout</button>
            </div>
            <div className="contentsub">
                <div className="rectangle">
                    Subscription with unpaid invoices
                    <i className="fas fa-folder"></i>
                </div>
                <div>
                    <section>
                        <table className="tableSubscription">
                            <thead>
                            <tr>
                                <th scope="col">Subscription Code</th>
                                <th scope="col">Start Date</th>
                                <th scope="col">Subscription Cost</th>
                                <th scope="col">Subscription Type</th>
                                <th scope="col">Subscription State</th>
                                <th scope="col">Invoices</th>
                            </tr>
                            </thead>
                            <tbody>
                            {subscriptions.map((subscription, index) => (
                                <tr key={index}>
                                    <td>{subscription.codeSubscription}</td>
                                    <td>{subscription.startDate.substring(0, 10)}</td>
                                    <td>{subscription.subscriptionCost}</td>
                                    <td>{subscription.subscriptionType}</td>
                                    <td>{subscription.subscriptionState}</td>
                                    <td>
                                        <Link to={`/DunningSubscription/${subscription.idSubscription}`}>
                                            &#128065;
                                        </Link>
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