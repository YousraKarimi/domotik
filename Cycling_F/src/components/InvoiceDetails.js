import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {Link, useParams} from 'react-router-dom';
import { GET_INVOICEDETAILS_BY_ID } from '../constants/back';

export default function InvoiceDetails() {
    const { invoiceId } = useParams();
    const [invoiceDetails, setInvoiceDetails] = useState({});

    useEffect(() => {
        console.log('Invoice ID:', invoiceId);

        const fetchInvoiceDetails = async () => {
            try {
                const response = await axios.get(GET_INVOICEDETAILS_BY_ID(invoiceId));
                setInvoiceDetails(response.data);
            } catch (error) {
                console.error('Error occurred while loading invoice details:', error);
                alert('Error Occurred while loading invoice details:' + error);
            }
        };
        fetchInvoiceDetails();
    }, [invoiceId]);

    return (
        <div className="container text-center">
            <h4 className="mx-2">Invoice Details</h4>
            <div className="row">
                <table className="table table-sm table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Invoice Number</th>
                        <th scope="col">Due Date</th>
                        <th scope="col">Amount Paid</th>
                        <th scope="col">Total Amount</th>
                        {/*<th scope="col"> view</th>*/}
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{ invoiceDetails.idInvoice }</td>
                        <td>{invoiceDetails.dueDate}</td>
                        <td>{invoiceDetails.amountPaid}</td>
                        <td>{invoiceDetails.totalAmount}</td>
                        {/*<td className="status">*/}
                        {/*    <Link to={`/InvoiceDetails/${invoiceDetails.idDetails}`}>*/}
                        {/*        &#128065;*/}
                        {/*    </Link>*/}
                        {/*    </td>*/}
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
}
