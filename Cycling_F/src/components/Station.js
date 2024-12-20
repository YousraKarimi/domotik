import React, { useEffect, useState } from 'react';
import axios from "axios";
//interaction between server and API
import '../styles/Sample.css';
import {INSERT_STATION, UPDATE_STATION, LOCAL_HOST_STATIONS
} from "../constants/back";

export default function Station() {
    const [stations, setStations] = useState([]);
    const setStationData = async () => {
        axios.get(LOCAL_HOST_STATIONS).then((response) => {
            setStations(response.data);
        }).catch(error => {
            alert("Error Ocurred while loading data:" + error);
        });
    }


    // const confirmRemoveStation = (id) => {
    //     if (window.confirm('Are you sure?')) {
    //         // Delete it!
    //         removeStation(id);
    //     }
    // };
    // const removeStation = async (id) => {
    //     axios.delete(LOCAL_HOST_STATION + id).then((response) => {
    //         setStationData();
    //     }).catch(error => {
    //         alert("Error Ocurred in removeSample:" + error);
    //     });
    // }

    const addStation = async () => {
        const newStation = {
            stationName: document.getElementById("nameInput").value,
            stationLocation: document.getElementById("locationInput").value,
            latitude: document.getElementById("latitudeInput").value,
            longitude: document.getElementById("longitudeInput").value,
            capacity: document.getElementById("capacityInput").value,


        };
        axios.post(INSERT_STATION,newStation ).then((response) => {
            document.getElementById("nameInput").value = "";
            document.getElementById("locationInput").value = "";
            document.getElementById("latitudeInput").value = 0;
            document.getElementById("longitudeInput").value = 0;
            document.getElementById("capacityInput").value = 0;
            setStationData();
        }).catch(error => {
            alert("Error Ocurred in insert:" + error);
        });
    }

    const handleChangeName = (idStation, newName) => {
        setStations( prevData => prevData.map(
            row => row.id === idStation ? { ...row, stationName: newName} :row)
        );
    }
    const updateName = async (sample) => {
        axios.post(UPDATE_STATION, sample).then((response) => {
            setStationData();
        }).catch(error => {
            alert("Error Ocurred in updateDate:" + error);
        });
    }

// managing side effects
    useEffect(() => {
        setStationData();
    }, []);


    const [name,setName]=useState();
    const [value, setValue]=useState(0);
    const add=()=>{
        setValue(value+1)
    }
    const substract=() =>{
        if (value > 0) {
            setValue(value - 1)
        }
    }

    return (
        <div className="container text-center">
            <h4 className=" mb-2">Station registration</h4>
            <div>
                <label className="mx-1 mb-2">Station Name: </label>
                <input
                    type='text'
                    className="mx-2 mb-2"
                    placeholder="Enter station name"
                    id="nameInput"
                    required
                    pattern="^[a-zA-Z0-9\s]+$"
                    title="Please enter a valid station name (only letters, numbers, and spaces are allowed)."
                />
            </div>
            <div>
                <label className="mx-1 mb-2">Station Location: </label>
                <input
                    type='text'
                    className="mx-2 mb-2"
                    placeholder="Enter station location"
                    id="locationInput"
                    required
                    pattern="^[a-zA-Z0-9\s]+$"
                    title="Please enter a valid station location (only letters, numbers, and spaces are allowed)."
                />
            </div>
            <div>
                <label className="mx-1 mb-2">Latitude: </label>
                <input
                    type='text'
                    className="mx-2 mb-2"
                    placeholder="Enter latitude"
                    id="latitudeInput"
                    required
                    pattern="[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?)"
                    title="Please enter a valid latitude."
                />
            </div>
            <div>
                <label className="mx-1 mb-2">Longitude: </label>
                <input
                    type='text'
                    className="mx-2 mb-2"
                    placeholder="Enter longitude"
                    id="longitudeInput"
                    required
                    pattern="[-+]?(180(\.0+)?|((1[0-7]\d)|(\d{1,2}))(\.\d+)?)"
                    title="Please enter a valid longitude."
                />
            </div>
            <div>
                <label className="mx-1 mb-2">Capacity: </label>
                <input
                    type='number'
                    step='1'
                    className="mx-2 mb-2"
                    placeholder="Enter capacity"
                    id="capacityInput"
                    required
                    pattern="\d+"
                    title="Please enter a valid integer."
                />
            </div>


            <button onClick={addStation} className="mx-1 mb-3" className="custom-button" > add </button>

            <h4 className=" mx-2">Station List</h4>
            <div className="row">
                <table className="table table-sm table-bordered table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Location</th>
                        <th scope="col">Latitude</th>
                        <th scope="col">Longitude</th>
                        <th scope="col">Capacity</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody className="table-group-divider">
                    {
                        stations.map( (station, index) => (
                            <tr key={index}>
                                <th scope="row">{station.id}</th>
                                <td>
                                    <div className="input-group mb-3">
                                        <input type="text"
                                               className="form-control"
                                               aria-describedby="button-addon2"
                                               value={station.stationName}
                                               onChange={e => handleChangeName(station.id, e.target.value)}
                                        />
                                        <button className="btn btn-outline-primary"
                                                type="button"
                                                id="button-addon2"
                                                onClick={() => updateName(station)}
                                        >Save</button>
                                    </div>
                                </td>
                                <td>{station.stationLocation}</td>
                                <td>{station.latitude}</td>
                                <td>{station.longitude}</td>
                                <td>{station.capacity}</td>
                                <td>
                                    <button type="button"
                                            className="btn btn-outline-danger"
                                            // onClick={() => confirmRemoveStation(station.id)}
                                    >Delete</button>
                                </td>
                            </tr>

                        ))
                    }

                    </tbody>
                </table>
            </div>
        </div>
    );
};

