import React, { useEffect, useState } from 'react';
import '../styles/Login.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    let navigate = useNavigate();

    useEffect(() => {
        document.body.classList.add('login-page');

        return () => {
            document.body.classList.remove('login-page');
        };
    }, []);

    const handleLogin = async (e, loginEndpoint) => {
        e.preventDefault();
        try {
            const response = await axios.post(`http://localhost:8083/Customer/${loginEndpoint}`, {
                userName: username,
                password: password,
            });
            if (loginEndpoint === 'accountant/loginAccountant') {
                navigate('/DunningSubscription');
            } else if (loginEndpoint === 'admin/loginAdmin') {
                localStorage.setItem('username', username);
                navigate('/invoice');
            } else if (loginEndpoint === 'customerAccount/loginCustomer') {
                localStorage.setItem('username', username);
                navigate('/invoiceClient');
            } else {
                setError('Invalid username or password');
            }
        } catch (error) {
            console.error('Error logging in:', error);
            setError('Invalid username or password');
        }
    };

    return (
        <div className="login-page-container">
            <div className="login-background">
                <div className="login-container">
                    <form className="login-form">
                        <h2>Login</h2>
                        {error && <div className="error-message">{error}</div>}
                        <input
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                        <button onClick={(e) => handleLogin(e, 'accountant/loginAccountant')} type="button">Login as Accountant</button>
                        <button onClick={(e) => handleLogin(e, 'admin/loginAdmin')} type="button">Login as Admin</button>
                        <button onClick={(e) => handleLogin(e, 'customerAccount/loginCustomer')} type="button">Login as Customer</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Login;
