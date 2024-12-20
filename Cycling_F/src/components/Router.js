import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import App from "./App";
import Navbar from "./Navbar";
import NotFound from "./NotFound";
import DunningDocuments from "./DunningDocuments";
import '../styles/App.css';
import DunningInvoice from "./DunningInvoice";
import DunningSubscription from "./DunningSubscription";
import Invoice from "./Invoice";
import InvoiceDetails from "./InvoiceDetails";
import ShowInvoice from "./ShowInvoice";
import Map from "./Map";
import Station from "./Station";
import BikeTable from "./BikeTable";
import Dashboard from "./Dashboard";
import DetailsTemplates from "./DetailsTemplates";
import DunningLevel from "./DunningLevel";
import DunningActions from "./DunningActions";
import TextEditor from "./TextEditor";
import Editor from "./Editor";
import Login from "./Login";
import InvoiceClient from "./InvoiceClient";
import '../styles/App.css';


export default function Router () {
    return (
        <BrowserRouter>
            <div>
                <Navbar />
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route path="/invoice" element={<Invoice />} />
                    <Route path="/invoiceClient" element={<InvoiceClient />} />
                    <Route path="/invoiceDetails/:invoiceId" element={<InvoiceDetails />} />
                    <Route path="/DunningDocumentsC" element={<DunningDocuments />}/>
                    <Route path="/DunningSubscription/:subscriptionId" element={<DunningInvoice />} />
                    <Route path="/DunningSubscription" element={<DunningSubscription />} />
                    <Route path="/DunningInvoice/:invoiceId" element={<ShowInvoice />} />
                    <Route path="/BikeTable" element={<BikeTable/>} />
                    <Route path="/map" element={<Map />}/>
                    <Route path="/station" element={<Station />}/>
                    <Route path="*" element={<NotFound />} />
                    <Route path="/Dunning" element={<Dashboard />} />
                    <Route path="/DetailsTemplates" element={<DetailsTemplates />} />
                    <Route path="/DunningLevel" element={<DunningLevel />} />
                    <Route path="/DunningActions" element={<DunningActions />} />
                    <Route path="/DetailsTemplates1/:templateId" element={<TextEditor />} />
                    <Route path="/DetailsTemplates2/:templateId" element={<Editor />} />
                    <Route path="/login" element={<Login />} />
                </Routes>
            </div>
        </BrowserRouter>
    );
};
