import React, {useState} from 'react';
//interaction between server and API
import '../styles/App.css';

export default function dunningDocuments() {


    // eslint-disable-next-line react-hooks/rules-of-hooks
    const [date,setDate]=useState();
    function getCurrentDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }


    return (

        <div className="container text-center">
            <h4 className=" mb-2">Dunning Document Form</h4>

            <div className="row mb-2">
                <div className="col-md-6">
                    <label className="mx-1 mb-2" >End Date :</label>
                    <input type="date"  className="form-control mx-2 mb-2" min={getCurrentDate()} defaultValue={getCurrentDate()} onChange={e=>setDate(e.target.value)} id="stringInput" />
                </div>

                <div className="col-md-6">
                    <label className="mx-1 mb-2">Start Date :</label>
                    <input type="date"  className="form-control mx-2 mb-2" min={getCurrentDate()} defaultValue={getCurrentDate()} onChange={e=>setDate(e.target.value)} id="stringInput" />
                </div>
            </div>

            <div className="row mb-2">
                <div className="col-md-6">
                    <label className="mx-1 mb-2">Action Taken :</label>
                    <input type="text" className="form-control mx-2 mb-2" placeholder="Action Taken" id="stringInput" />
                </div>

                <div className="col-md-6">
                    <label className="mx-1 mb-2">Date Action Taken :</label>
                    <input type="date" className="form-control mx-2 mb-2" min={getCurrentDate()} defaultValue={getCurrentDate()} onChange={e=>setDate(e.target.value)} id="stringInput" />
                </div>
            </div>

            <div className="row mb-2">
                <div className="col-md-6">
                    <label className="mx-1 mb-2">Next Action :</label>
                    <input type="text" className="form-control mx-2 mb-2" placeholder="Next Action" id="stringInput" />
                </div>

                <div className="col-md-6">
                    <label className="mx-1 mb-2">Date Next Action :</label>
                    <input type="date" className="form-control mx-2 mb-2" min={getCurrentDate()} defaultValue={getCurrentDate()} onChange={e=>setDate(e.target.value)} id="stringInput" />
                </div>
            </div>

            <div className="row mb-2">
                <div className="col-md-6">
                    <label className="mx-1 mb-2">dunning Level :</label>
                    <input type="text" className="form-control mx-2 mb-2"  id="stringInput" />
                </div>

                <div className="col-md-6">
                    <label className="mx-1 mb-2">dunning Status :</label>
                    <input type="text" className="form-control mx-2 mb-2"  id="stringInput" />
                </div>
            </div>

            <div className="row mb-2">
                <div className="col-md-6">
                    <label className="mx-1 mb-2">Pause Duration :</label>
                    <input type="text" className="form-control mx-2 mb-2 " placeholder="Pause Duration " id="stringInput" />
                </div>

                <div className="col-md-6">
                    <div><label className="mx-1 mb-2">penalty :</label>
                        <input type='text' className="form-control mx-2 mb-2 " placeholder="Penalty" id="stringInput" /></div>
                </div>
            </div>

            <button  className="mx-1 mb-3" className="neon-button" > add </button>

        </div>
    );

};
