import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import '../styles/App.css';
import Login from "./Login";
import '../styles/App.css';
import NotFound from "./NotFound";
import Dashboard from "./Dashboard";
import ConfigurationList from "./ConfigurationList";
import ConfigurationForm from "./ConfigurationForm";
import ConfigurationUpdateList from "./ConfigurationUpdateList";
import UpdateConfiguration from "./UpdateConfiguration";

import DeviceList from "./DeviceList";
import Notifications from "./Notifications";



export default function Router () {
    return (
        <BrowserRouter>
            <div>
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="*" element={<NotFound />} />
                    <Route path="/dashboard" element={<Dashboard />} />
                    <Route path="/viewConfigs" element={<ConfigurationList />} />
                    <Route path="/myDevices" element={<DeviceList />} />
                    <Route path="/updateConfig" element={<ConfigurationUpdateList />} />
                    <Route path="/addConfig" element={<ConfigurationForm />} />
                    <Route path="/notifications" element={<Notifications />} />
                    <Route path="/update-configuration/:deviceId" element={<UpdateConfiguration />} />

                </Routes>
            </div>
        </BrowserRouter>
    );
};
