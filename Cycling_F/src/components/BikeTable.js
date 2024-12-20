import React, { useEffect, useState } from 'react';
import { LOCAL_HOST_ALLBIKES } from "../constants/back";
import '../styles/BikeTable.css';

const BikeTable = () => {
    const [bikes, setBikes] = useState([]);

    const fetchBikes = async () => {
        try {
            const response = await fetch(LOCAL_HOST_ALLBIKES);
            const data = await response.json();
            setBikes(data);
        } catch (error) {
            console.error("Error fetching bike data:", error);
        }
    };

    useEffect(() => {
        fetchBikes(); // Initial fetch
        const interval = setInterval(fetchBikes, 1000); // Fetch every second

        return () => clearInterval(interval); // Cleanup interval on component unmount
    }, []);

    return (
        <div className="bike-table-container">
            <h1 className="bike-data-heading">Bike Data</h1>
            <table className="bike-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Station</th>
                    <th>Serial Number</th>
                    <th>Availability</th>
                    <th>Time Entered In Circulation</th>
                </tr>
                </thead>
                <tbody>
                {bikes.map(bike => (
                    <tr key={bike.idBike}>
                        <td>{bike.idBike}</td>
                        <td>{bike.station ? bike.station.stationName : 'N/A'}</td>
                        <td>{bike.serialNumber}</td>
                        <td>{bike.availability}</td>
                        <td>{bike.timeEnteredInCirculation}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default BikeTable;
