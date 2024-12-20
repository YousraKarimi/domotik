import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { GET_INVOICES, GET_INVOICEDETAILS_BY_DUE_DATE, SEARCH_INVOICES_BY_NAME } from '../constants/back';
import ReactPaginate from 'react-paginate';
import '../styles/Invoice.css';

export default function Invoice() {
    const [invoices, setInvoices] = useState([]);
    const [selectedDate, setSelectedDate] = useState(null);
    const [nameFilter, setNameFilter] = useState('');
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [suggestions, setSuggestions] = useState([]);
    const [adminUsername, setAdminUsername] = useState('');
    let navigate = useNavigate();

    const handleDateChange = (e) => {
        const inputDate = e.target.value;
        setSelectedDate(inputDate);
    };

    const handleNameFilterChange = async (e) => {
        const newNameFilter = e.target.value;
        setNameFilter(newNameFilter);

        try {
            if (newNameFilter.trim() !== '') {
                const response = await axios.get(SEARCH_INVOICES_BY_NAME(newNameFilter));
                const names = response.data.map(entry => ({ firstName: entry.firstName, lastName: entry.lastName }));
                setSuggestions(names);
            } else {
                setSuggestions([]);
            }
        } catch (error) {
            console.error('Error fetching suggestions:', error);
            setError('Error fetching suggestions: ' + error.message);
            setSuggestions([]);
        }
    };

    useEffect(() => {
        const fetchInvoices = async () => {
            try {
                setLoading(true);
                setError(null);
                let apiUrl = selectedDate
                    ? GET_INVOICEDETAILS_BY_DUE_DATE(selectedDate)
                    : GET_INVOICES;

                if (nameFilter) {
                    const [firstName, lastName] = nameFilter.split(' ');
                    apiUrl = SEARCH_INVOICES_BY_NAME(firstName, lastName);
                }

                const response = await axios.get(apiUrl);
                setInvoices(response.data);
                setTotalPages(Math.ceil(response.data.length / 10));
            } catch (error) {
                console.error('Error occurred while loading invoices:', error);
                console.error('API Response:', error.response?.data);
                setError('Error Occurred while loading invoices: ' + error.message);
            } finally {
                setLoading(false);
            }
        };

        const fetchAdminUsername = () => {
            const username = localStorage.getItem('username');
            setAdminUsername(username);
        };

        fetchInvoices();
        fetchAdminUsername();
    }, [selectedDate, nameFilter]);

    const handlePageClick = (data) => {
        let selected = data.selected;
        setCurrentPage(selected);
    };

    const handleLogout = () => {
        localStorage.removeItem('username');
        navigate('/login');
    };

    return (
        <div className="container text-center">
            <h4 className="mx-2">Invoices</h4>
            <h2>Hello, {adminUsername}! Here are the invoices:</h2>
            <button className="logout-buttonA" onClick={handleLogout}>Logout</button>
            <div className="row">
                <div>
                    <label>Select Due Date: </label>
                    <input
                        type="date"
                        onChange={handleDateChange}
                    />
                </div>
                <div>
                    <label>Filter by Name: </label>
                    <input
                        type="text"
                        value={nameFilter}
                        onChange={handleNameFilterChange}
                    />
                    <ul>
                        {error && <p style={{ color: 'red' }}>Error: {error}</p>}
                        {suggestions.map((name, index) => (
                            <li key={index}>{name.firstName} {name.lastName}</li>
                        ))}
                    </ul>
                </div>
                {loading && <p>Loading invoices...</p>}
                {error && <p style={{ color: 'red' }}>Error: {error}</p>}
                {invoices.length === 0 && !loading && <p>No invoices found.</p>}
                <table className="table table-sm table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Invoice</th>
                        <th scope="col">Subscription</th>
                        <th scope="col">Total Unpaid</th>
                        <th scope="col">Status</th>
                        <th scope="col">Details</th>
                    </tr>
                    </thead>
                    <tbody>
                    {invoices.slice(currentPage * 10, (currentPage + 1) * 10).map((invoice, index) => (
                        <tr key={index}>
                            <td>{invoice.subscription.customerAccount.individualClient.firstName}</td>
                            <td>{invoice.subscription.customerAccount.individualClient.lastName}</td>
                            <th scope="row">{invoice.invoiceNumber}</th>
                            <td>{invoice.subscriptionCode}</td>
                            <td>{invoice.totalUnpaid}</td>
                            <td>{invoice.invoiceStatus}</td>
                            <td>
                                <Link to={`/invoiceDetails/${invoice.idInvoice}`}>
                                    View Details
                                </Link>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>

                <ReactPaginate
                    previousLabel={<span className="previous">Previous</span>}
                    nextLabel={<span className="next">Next</span>}
                    breakLabel={'...'}
                    breakClassName={'break-me'}
                    pageCount={totalPages}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={handlePageClick}
                    containerClassName={'pagination'}
                    subContainerClassName={'pages pagination'}
                    activeClassName={'active'}
                />
            </div>
        </div>
    );
}