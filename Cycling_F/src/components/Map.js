import "../styles/Map.css";
import "leaflet/dist/leaflet.css";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import MarkerClusterGroup from "react-leaflet-cluster";
import { Icon, divIcon, point } from "leaflet";
import { useEffect, useState } from "react";
import axios from "axios";
import { NUMBEROFBIKES, LOCAL_HOST_STATIONS } from "../constants/back";

export default function Map() {
    const [stations, setStations] = useState([]);
    const [bikeCounts, setBikeCounts] = useState({});
    const setStationData = async () => {
        try {
            const stationsResponse = await axios.get(LOCAL_HOST_STATIONS);
            setStations(stationsResponse.data);

            const bikeCountsResponse = await axios.get(NUMBEROFBIKES);
            const counts = {};
            bikeCountsResponse.data.forEach((entry) => {
                const stationName = Object.keys(entry)[0];
                const bikeCount = entry[stationName];
                counts[stationName] = bikeCount;
            });
            setBikeCounts(counts);

        } catch (error) {
            alert("Error Occurred while loading data:" + error);
        }
    };

    // create custom icon
    const customIcon = new Icon({
        iconUrl: "https://cdn-icons-png.flaticon.com/512/447/447031.png",
        iconSize: [38, 38], // size of the icon
    });

    // custom cluster icon
    const createClusterCustomIcon = function (cluster) {
        return new divIcon({
            html: `<span class="cluster-icon">${cluster.getChildCount()}</span>`,
            className: "custom-marker-cluster",
            iconSize: point(33, 33, true),
        });
    };

    // markers
    const markers = stations.map((station, index) => ({
        key: index,
        geocode: [station.latitude, station.longitude],
        // To display stations info.
        popUp: (
            <div>
                <strong>{station.stationName}</strong>
                <br />
                <span>Address: {station.stationLocation}</span>
                <br />
                <span>
          Available: {bikeCounts[station.stationName] || 0}/{station.capacity}
        </span>
            </div>
        ),
    }));

    // managing side effects
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
            </MarkerClusterGroup>
        </MapContainer>
    );
}

