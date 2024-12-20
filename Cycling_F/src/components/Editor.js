import {Link, useParams} from "react-router-dom";
import React, { useEffect, useState } from "react";
import { ContentState, EditorState, convertToRaw } from "draft-js";
import axios from "axios";
import { Editor } from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import {
    GET_channel_TEMPLATE_ID,
    GET_laguage_TEMPLATE_ID,
    GET_templateName_TEMPLATE_ID,
    GET_Text_TEMPLATE_ID, UPDATE_Text_TEMPLATE_ID,
} from "../constants/back";

export default function EditorComponent() {
    const { templateId } = useParams();
    const [editorState, setEditorState] = useState(EditorState.createEmpty());
    const [templateName, setTemplateName] = useState('');
    const [language, setLanguage] = useState('');
    const [channel, setChannel] = useState('');

    const getTemplateDetails = async (templateId) => {
        try {
            const templateNameResponse = await axios.get(GET_templateName_TEMPLATE_ID(templateId));
            const languageResponse = await axios.get(GET_laguage_TEMPLATE_ID(templateId));
            const channelResponse = await axios.get(GET_channel_TEMPLATE_ID(templateId));
            const textResponse = await axios.get(GET_Text_TEMPLATE_ID(templateId));

            setTemplateName(templateNameResponse.data);
            setLanguage(languageResponse.data);
            setChannel(channelResponse.data);

            const textWithSingleNewLines = textResponse.data.replace(/(\r\n|\n|\r){2,}/g, "\n");
            const contentState = ContentState.createFromText(textWithSingleNewLines);
            setEditorState(EditorState.createWithContent(contentState));
        } catch (error) {
            console.error('Error occurred while loading data:', error);
            alert('Error occurred while loading data: ' + error);
        }
    };

    const updateTemplateText = async () => {
        try {
            const rawContentState = convertToRaw(editorState.getCurrentContent());
            const text = rawContentState.blocks.map(block => block.text).join('\n');
            await axios.put(UPDATE_Text_TEMPLATE_ID(templateId), { text });
            alert('Template updated successfully');
        } catch (error) {
            console.error('Error occurred while updating template:', error);
            alert('Error occurred while updating template: ' + error);
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
                Template Editor
                <i className="fa-solid fa-pen-to-square"></i>
            </div>
            <div className="text-editor-container">
                <div className="form-fields">
                    <label>
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
                <button onClick={updateTemplateText}>Save</button>
            </div>
        </div>
    );
}
