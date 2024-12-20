import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import { GET_INVOICES_FOR_SUBSCRIPTION } from '../constants/back';
import '../styles/DunningInvoice.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

export default function DunningInvoice() {
    const { subscriptionId } = useParams();
    const [invoices, setInvoices] = useState([]);

    const setInvoicesData = async () => {
        try {
            const response = await axios.get(GET_INVOICES_FOR_SUBSCRIPTION(subscriptionId));
            setInvoices(response.data);
        } catch (error) {
            console.error('Error occurred while loading invoices:', error);
            alert('Error Occurred while loading invoices:' + error);
        }
    };

    useEffect(() => {
        setInvoicesData();
    }, [subscriptionId]);

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
            <div className="contentinv">
                <div className="rectangleinv">
                    Invoices
                    <i className="fas fa-file-invoice"></i>
                    <div className="back-arrow">
                        <Link to="/DunningSubscription">
                            <i className="fas fa-arrow-left"></i>
                        </Link>
                    </div>
                </div>

                <div className="row">
                    <table className="tableinvoice">
                        <thead>
                        <tr>
                            <th scope="col">Invoice</th>
                            <th scope="col">Subscription</th>
                            <th scope="col">Status</th>
                            <th scope="col">view</th>
                        </tr>
                        </thead>
                        <tbody>
                        {invoices.map((invoice, index) => (
                            <tr key={index}>
                                <th scope="row">{invoice.invoiceNumber}</th>
                                <td>{invoice.subscriptionCode}</td>
                                <td>{invoice.invoiceStatus}</td>
                                <td>
                                    <Link to={`/DunningInvoice/${invoice.idInvoice}`} state={{ invoiceId: invoice.idInvoice }}>
                                        &#128065;
                                    </Link>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
