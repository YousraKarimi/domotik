import "../styles/Map.css";
import "leaflet/dist/leaflet.css";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import MarkerClusterGroup from "react-leaflet-cluster";
import { Icon, divIcon, point } from "leaflet";
import {useEffect, useState} from "react";
import axios from "axios";
import {LOCAL_HOST_BIKESINSTATION, LOCAL_HOST_STATIONS} from "../constants/back";





export default function MapStation() {

    const [station, setStation] = useState([]);
    const [stations, setStations] = useState([]);
    const [loadedBikes, setLoadedBikes] = useState({});



    const setStationData = async () => {
        axios.get(LOCAL_HOST_STATIONS).then((response) => {
            setStations(response.data);
        }).catch(error => {
            alert("Error Ocurred while loading data:" + error);
        });
    }
    // const getBikesInStation = async (id) => {
    //     axios.get(LOCAL_HOST_BIKESINSTATION+id).then((response) => {
    //         setStationData(response.data);
    //     }).catch(error => {
    //         alert("Error Ocurred while loading data:" + error);
    //     });
    // }

    // const getBikesInStation = async (id) => {
    //     try {
    //         const response = await axios.get(LOCAL_HOST_BIKESINSTATION + id);
    //         // Assuming the response includes both station and available bikes data
    //         setStationData(response.data);
    //     } catch (error) {
    //         alert("Error occurred while loading data: " + error);
    //     }
    // }

    // Function to fetch bikes in a station
    const getBikesInStation = async (id) => {
        try {
            const response = await axios.get(LOCAL_HOST_BIKESINSTATION + id);
            const bikesInStation = response.data;

            setStations((prevStations) => {
                return prevStations.map((station) => {
                    if (station.idStation === id) {
                        setLoadedBikes((prevLoadedBikes) => ({
                            ...prevLoadedBikes,
                            [id]: bikesInStation.length, // Assuming length is the property you want
                        }));
                        return { ...station, bikes: bikesInStation };
                    }
                    return station;
                });
            });
        } catch (error) {
            alert("Error occurred while loading data: " + error);
        }
    };

        // create custom icon
        const customIcon = new Icon({
            iconUrl: "https://cdn-icons-png.flaticon.com/512/447/447031.png",
            // iconUrl: require("./icons/placeholder.png"),
            iconSize: [38, 38] // size of the icon
        });

        // custom cluster icon
        const createClusterCustomIcon = function (cluster) {
            return new divIcon({
                html: `<span class="cluster-icon">${cluster.getChildCount()}</span>`,
                className: "custom-marker-cluster",
                iconSize: point(33, 33, true)
            });
        };


// markers

    const markers = stations.map((station, index) => ({
        key: index,
        geocode: [station.latitude, station.longitude],
        // To display stations info.`
        popUp: (
            <div>
                <strong>{station.stationName}</strong>
                <br />
                <span>Address: {station.stationLocation}</span>
                <br />
                <span>Available: {station.quantityOfBicyclesAvailable}/{station.capacity}</span>
                <br />
                <span>Available: {loadedBikes[station.id] || 0}/{station.capacity}</span>

            </div>
        ),
    }));


        // managing side effects
        // eslint-disable-next-line react-hooks/rules-of-hooks
        useEffect(() => {
            setStationData();
        }, []);


        return (
            <MapContainer center={[48.8566, 2.3522]} zoom={13}>
                {/* OPEN STREEN MAPS TILES */}
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {/* WATERCOLOR CUSTOM TILES */}
                {/* <TileLayer
        attribution='Map tiles by <a href="http://stamen.com">Stamen Design</a>, <a href="http://creativecommons.org/licenses/by/3.0">CC BY 3.0</a> &mdash; Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://stamen-tiles-{s}.a.ssl.fastly.net/watercolor/{z}/{x}/{y}.jpg"
      /> */}
                {/* GOOGLE MAPS TILES */}
                {/* <TileLayer
        attribution="Google Maps"
        // url="http://{s}.google.com/vt/lyrs=m&x={x}&y={y}&z={z}" // regular
        // url="http://{s}.google.com/vt/lyrs=s,h&x={x}&y={y}&z={z}" // satellite
        url="http://{s}.google.com/vt/lyrs=p&x={x}&y={y}&z={z}" // terrain
        maxZoom={20}
        subdomains={["mt0", "mt1", "mt2", "mt3"]}
      /> */}

                <MarkerClusterGroup
                    chunkedLoading
                    iconCreateFunction={createClusterCustomIcon}
                >
                    {/* Mapping through the markers */}
                    {markers.map((marker) => (
                        <Marker position={marker.geocode} icon={customIcon}>
                            <Popup>{marker.popUp}</Popup>
                        </Marker>
                    ))}

                    {/* Hard coded markers */}
                    {/* <Marker position={[51.505, -0.09]} icon={customIcon}>
          <Popup>This is popup 1</Popup>
        </Marker>
        <Marker position={[51.504, -0.1]} icon={customIcon}>
          <Popup>This is popup 2</Popup>
        </Marker>
        <Marker position={[51.5, -0.09]} icon={customIcon}>
          <Popup>This is popup 3</Popup>
        </Marker>
       */}
                </MarkerClusterGroup>
            </MapContainer>
        );
    }
