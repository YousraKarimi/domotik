import React, { useEffect, useState } from "react";
import {Link, useParams} from "react-router-dom";
import { EditorState, ContentState } from "draft-js";
import { Editor } from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import axios from "axios";
import '../styles/TextEditor.css';
import {
    GET_channel_TEMPLATE_ID,
    GET_laguage_TEMPLATE_ID, GET_templateName_TEMPLATE_ID, GET_Text_TEMPLATE_ID,
    GET_LEVEL_TEMPLATE_ID, GET_ACTION_TEMPLATE_ID
} from "../constants/back";

export default function TextEditor() {
    const { templateId } = useParams();
    const [editorState, setEditorState] = useState(EditorState.createEmpty());
    const [templateName, setTemplateName] = useState('');
    const [language, setLanguage] = useState('');
    const [channel, setChannel] = useState('');
    const [level, setLevel] = useState('');
    const [action, setAction] = useState('');

    const getTemplateDetails = async (templateId) => {
        try {
            const templateNameResponse = await axios.get(GET_templateName_TEMPLATE_ID(templateId));
            const languageResponse = await axios.get(GET_laguage_TEMPLATE_ID(templateId));
            const channelResponse = await axios.get(GET_channel_TEMPLATE_ID(templateId));
            const textResponse = await axios.get(GET_Text_TEMPLATE_ID(templateId));
            const levelResponse = await axios.get(GET_LEVEL_TEMPLATE_ID(templateId));
            const actionResponse = await axios.get(GET_ACTION_TEMPLATE_ID(templateId));

            setTemplateName(templateNameResponse.data);
            setLanguage(languageResponse.data);
            setChannel(channelResponse.data);
            setLevel(levelResponse.data);
            setAction(actionResponse.data);

            const textWithSingleNewLines = textResponse.data.replace(/(\r\n|\n|\r){2,}/g, "\n");
            const contentState = ContentState.createFromText(textWithSingleNewLines);
            setEditorState(EditorState.createWithContent(contentState));
        } catch (error) {
            console.error('Error occurred while loading data:', error);
            alert('Error occurred while loading data: ' + error);
        }
    };

    useEffect(() => {
        getTemplateDetails(templateId);
    }, [templateId]);

    const onEditorStateChange = (newEditorState) => {
        setEditorState(newEditorState);
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
            <div className="rectangleEditor">
                Show Template
                <i className="fa-regular fa-eye"></i>
            </div>
            <div className="text-editor-container">
                <div className="form-fields">
                    <label className="Template name">
                        Template Name:
                        <input
                            type="text"
                            name="templateName"
                            value={templateName}
                            readOnly
                        />
                    </label>
                    <label>
                        Language:
                        <input
                            type="text"
                            name="language"
                            value={language}
                            readOnly
                        />
                    </label>
                    <label>
                        Channel:
                        <input
                            type="text"
                            name="channel"
                            value={channel}
                            readOnly
                        />
                    </label>
                </div>
                <div className="editor-wrapper">
                    <Editor
                        editorState={editorState}
                        toolbarClassName="toolbarClassName"
                        wrapperClassName="wrapperClassName"
                        editorClassName="editorClassName"
                        onEditorStateChange={onEditorStateChange}
                    />
                </div>
            </div>
            <div className="empty-table-div">
                <p className="title">Related Dunning Level and Action  :</p>
                <div>
                    <p className="label">Dunning Level :</p>
                    <p className="value">{level}</p>
                </div>
                <div>
                    <p className="label">Dunning Action Name :</p>
                    <p className="value">{action}</p>
                </div>
            </div>
        </div>
    );
}
