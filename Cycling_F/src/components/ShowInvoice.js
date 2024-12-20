import axios from 'axios';
import { useParams, Link } from 'react-router-dom';
import { useEffect, useState } from "react";
import {
    GET_FIRST_NAME_BY_INVOICE_ID,
    GET_LAST_NAME_BY_INVOICE_ID,
    GET_PERIOD_OF_INVOICE,
    GET_DUEDATE_INVOICE_ID,
    GET_PHONE_BY_INVOICE_ID,
    GET_EMAIL_BY_INVOICE_ID,
    GET_ADRESS_BY_INVOICE_ID,
    GET_DEADLINE_PAIEMENT_OF_INVOICE,
    GET_SUBSCRIPTION_COST,
    GET_SUBSCRIPTION_CODE,
    GET_ESCOMPTE_OF_INVOICE
} from '../constants/back';
import "../styles/Invoice.css";
import cyclingImage from '../assets/cycling.png';

export default function ShowInvoice() {
    const { invoiceId } = useParams();
    const [dueDate, setDueDate] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState('');
    const [adress, setAdress] = useState('');
    const [period, setPeriod] = useState('');
    const [codeSubscription, setCodeSubscription] = useState('');
    const [subscriptionCost, setSubscriptionCost] = useState('');
    const [deadlinePaiement, setDeadlinePaiement] = useState('');
    const [escompte, setEscompte] = useState('');

    const getInvoiceDetails = async (invoiceId) => {
        try {
            const codeSubscriptionResponse = await axios.get(GET_SUBSCRIPTION_CODE(invoiceId));
            const subscriptionCostResponse = await axios.get(GET_SUBSCRIPTION_COST(invoiceId));
            const dueDateResponse = await axios.get(GET_DUEDATE_INVOICE_ID(invoiceId));
            const firstNameResponse = await axios.get(GET_FIRST_NAME_BY_INVOICE_ID(invoiceId));
            const phoneResponse = await axios.get(GET_PHONE_BY_INVOICE_ID(invoiceId));
            const emailResponse = await axios.get(GET_EMAIL_BY_INVOICE_ID(invoiceId));
            const adressResponse = await axios.get(GET_ADRESS_BY_INVOICE_ID(invoiceId));
            const lastNameResponse = await axios.get(GET_LAST_NAME_BY_INVOICE_ID(invoiceId));
            const periodResponse = await axios.get(GET_PERIOD_OF_INVOICE(invoiceId));
            const deadlinePaiementResponse = await axios.get(GET_DEADLINE_PAIEMENT_OF_INVOICE(invoiceId));
            const escompteResponse = await axios.get(GET_ESCOMPTE_OF_INVOICE(invoiceId));

            setCodeSubscription(codeSubscriptionResponse.data);
            setSubscriptionCost(subscriptionCostResponse.data);
            setDueDate(dueDateResponse.data);
            setFirstName(firstNameResponse.data);
            setLastName(lastNameResponse.data);
            setPhone(phoneResponse.data);
            setEmail(emailResponse.data);
            setAdress(adressResponse.data);
            setPeriod(periodResponse.data);
            setDeadlinePaiement(deadlinePaiementResponse.data);
            setEscompte(escompteResponse.data);
        } catch (error) {
            console.error('Error occurred while loading data:', error);
            alert('Error Occurred while loading data:' + error);
        }
    };

    useEffect(() => {
        getInvoiceDetails(invoiceId);
    }, [invoiceId]);

    return (
        <div className="container mt-5">
            <div className="back-arrow">
                <Link to={`/DunningSubscription`}>
                    <i className="fas fa-arrow-left"></i>
                </Link>
            </div>
            <div className="invoice">
                <div className="invoice-header">
                    <img src={cyclingImage} alt="cyclingImage" className="invoice-logo"></img>
                    <div>
                        <div className="invoice-title">Facture</div>
                        <div className="invoice-number">Numéro de Facture: <span id="invoiceNumber">{"FACN" + invoiceId}</span></div>
                        <div><strong>Date de la Facture:</strong> <span id="dueDate">{dueDate.substring(0, 10)}</span></div>
                    </div>
                </div>
                <div className="invoice-details">
                    <div className="invoice-from">
                        <h6 className="mb-3">De:</h6>
                        <strong>Cycling</strong>
                        <br />71 Rue Saint-Simon, Créteil<br />
                        Email: <a href="mailto:votre@email.com">DunCycle@mail.fr</a><br />
                        Téléphone: 123-456-7890
                    </div>
                    <div className="invoice-to">
                        <h6 className="mb-3">À:</h6>
                        <strong>{`${firstName} ${lastName}`}</strong>
                        <br />{adress}<br />
                        Email: <a href="mailto:client@email.com">{email}</a><br />
                        Téléphone: {phone}
                    </div>
                </div>
                <div className="table-responsive" style={{ marginTop: '30px' }}>
                    <table className="invoice-table th">
                        <thead>
                        <tr>
                            <th>Description</th>
                            <th>Période</th>
                            <th>Quantité</th>
                            <th>Prix unitaire</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Abonnement (Code: <span id="subscriptionCode">{codeSubscription}</span>)</td>
                            <td><span id="period">{period}</span></td>
                            <td>1</td>
                            <td id="unitPrice">{subscriptionCost + " €"}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div className="conditions-modalites">
                    <h4>Conditions et Modalités</h4>
                    <div className="border p-3" style={{ marginTop: '20px' }}>
                        <p><strong>Date limite de paiement:</strong> <span id="deadlinePaymentConditions">{deadlinePaiement.substring(0, 10)}</span></p>
                        <p><strong>Taux d'escompte:</strong> <span id="totalUnpaidConditions">{escompte + " €"}</span></p>
                        <p><strong>En cas de retard de paiement:</strong> Application des intérêts de retard au taux légal en vigueur.</p>
                    </div>
                </div>
                <div className="resolution-text">
                    <p><strong>Nous vous invitons à régler votre facture dès que possible pour éviter tout désagrément. Merci!</strong></p>
                </div>
            </div>
        </div>
    );
}
