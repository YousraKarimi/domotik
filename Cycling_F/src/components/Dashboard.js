import React, { useState } from 'react';
import '../styles/Dashboard.css';
import {Link} from "react-router-dom";

export default function Dashboard() {
    const [note, setNote] = useState('');
    const [savedNote, setSavedNote] = useState('');

    const handleNoteChange = (event) => {
        setNote(event.target.value);
    };

    const handleNoteButtonClick = () => {
        setSavedNote(note);
        setNote('');
    };

    return (
        <div>
            <div className="navbar1">
                <ul className="ul1">
                    <li><Link to="/DunningSubscription">Debts</Link></li>
                    <li><Link to="/DetailsTemplates">Templates</Link></li>
                    <li><Link to="/DunningLevel">Levels</Link></li>
                    <li><Link to="/DunningActions">Actions</Link></li>
                    <li><Link to="/DunningDocumentsC">Settings</Link></li>
                </ul>
            </div>
            <div className="content">
                <div className="left-column">
                    <div className="chart">Bar Chart 1</div>
                    <div className="chart">Bar Chart 2</div>
                </div>
                <div className="middle-column">
                    <div className="vertical-chart">Vertical Bar Chart</div>
                </div>
                <div className="right-column">
                    <div className="statistics">Statistics 1</div>
                    <div className="statistics">Statistics 2</div>
                    <div className="statistics">Statistics 3</div>
                    <div className="information">
                    <textarea
                        className="note-textarea"
                        placeholder="Leave a note..."
                        value={note}
                        onChange={handleNoteChange}
                    />
                        <button
                            className="note-button"
                            onClick={handleNoteButtonClick}
                        >
                            Save Note
                        </button>
                    </div>
                </div>
            </div>
            <div className="note-container"><strong>Saved Note: </strong>

                {savedNote && (
                    <div className="saved-note">
                         {savedNote}
                    </div>
                )}
            </div>
        </div>
    );
};
