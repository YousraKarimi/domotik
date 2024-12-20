import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { SEARCH_INVOICES_BY_USERNAME } from '../constants/back';
import '../styles/Invoice.css';
import { Link, useNavigate } from 'react-router-dom';

export default function InvoiceClient() {
    const [invoices, setInvoices] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [username, setUsername] = useState('');
    let navigate = useNavigate();

    useEffect(() => {
        const fetchInvoices = async () => {
            try {
                setLoading(true);
                setError(null);

                const storedUsername = localStorage.getItem('username');
                if (!storedUsername) {
                    setError('Username not found in local storage');
                    setLoading(false);
                    return;
                }

                setUsername(storedUsername);
                const response = await axios.get(SEARCH_INVOICES_BY_USERNAME(storedUsername));
                setInvoices(response.data);
            } catch (error) {
                console.error('Error occurred while loading invoices:', error);
                console.error('API Response:', error.response?.data);
                setError('Error occurred while loading invoices: ' + error.message);
            } finally {
                setLoading(false);
            }
        };
        fetchInvoices();
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('username');
        navigate('/login');
    };

    return (
        <div className="container">
            <h1>Invoices</h1>
            <h2>Hello, {username}! Here are your invoices:</h2>
            <button className="logout-buttonA" onClick={handleLogout}>Logout</button>
            {loading && <p>Loading invoices...</p>}
            {error && <p>Error: {error}</p>}
            <table className="table">
                <thead>
                <tr>
                    <th>Invoice Number</th>
                    <th>Subscription</th>
                    <th>Total Unpaid</th>
                    <th>Details</th>
                </tr>
                </thead>
                <tbody>
                {invoices.map(invoice => (
                    <tr key={invoice.id}>
                        <td>{invoice.invoiceNumber}</td>
                        <td>{invoice.subscriptionCode}</td>
                        <td>{invoice.totalUnpaid}</td>
                        <td>
                            <Link to={`/invoiceDetails/${invoice.idInvoice}`}>
                                View Details
                            </Link>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}