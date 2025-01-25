import React, { useState } from 'react';
import '../styles/Login.css';
import HouseConfig from '../assets/House_Config.png';
import { useNavigate } from 'react-router-dom';
import { loginUser } from "../constants/back";

const Login = () => {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    let navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const userData = await loginUser(login, password);
            console.log('User logged in:', userData);
            localStorage.setItem('username', userData.fullName);
            navigate('/dashboard');
        } catch (error) {
            setError('Invalid username or password');
        }
    };

    return (
        <div className="login-page-container" style={{ backgroundImage: `url(${HouseConfig})` }}>
            <div className="login-container">
                <form className="login-form" onSubmit={handleLogin}>
                    <h2>Login</h2>
                    {error && <div className="error-message">{error}</div>}
                    <input
                        type="text"
                        placeholder="Username"
                        value={login}
                        onChange={(e) => setLogin(e.target.value)}
                        required
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <button type="submit">
                        Login
                    </button>
                </form>
            </div>
        </div>
    );
};

export default Login;
