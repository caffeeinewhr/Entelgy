import React, { useEffect, useState } from 'react';
import { findAllUsers } from '../services/UserService';
import './LoginPage.css';

const USERS_URL = 'http://localhost:8081/users';
const EVENTS_URL = 'http://localhost:8081/events';

export const LoginPage = () => {
    const [registerData, setRegisterData] = useState({
        nombre: '',
        correo: '',
        password: '',
        confirmPassword: ''
    });

    const [loginData, setLoginData] = useState({
        correo: '',
        password: ''
    });

    const [users, setUsers] = useState([]);

    const getUsers = async () => {
        const result = await findAllUsers();
        if (result && result.data && result.data._embedded && result.data._embedded.users) {
            setUsers(result.data._embedded.users);
            console.log(result.data._embedded.users);
        } else {
            console.log('No users found');
        }
    };

    useEffect(() => {
        getUsers();
    }, []);

    const handleRegisterChange = (e) => {
        const { name, value } = e.target;
        setRegisterData({ ...registerData, [name]: value });
    };

    const handleLoginChange = (e) => {
        const { name, value } = e.target;
        setLoginData({ ...loginData, [name]: value });
    };

    const handleRegisterSubmit = async (e) => {
        e.preventDefault();

        const response = await fetch(`${USERS_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(registerData)
        });

        const result = await response.json();
        console.log(result);
    };

    const handleLoginSubmit = async (e) => {
        e.preventDefault();

        const response = await fetch(`${EVENTS_URL}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        const result = await response.json();
        console.log(result);

        if (response.ok) {
            window.location.href = '/home';
        } else {
            alert('Login failed. Please check your credentials.');
        }
    };

    return (
        <div className="login-container">
            <div className="row">
                <div className="col-md-6 login-form-1">
                    <h3>Registro</h3>
                    <form onSubmit={handleRegisterSubmit}>
                        <div className="form-group mb-2">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Nombre"
                                name="nombre"
                                value={registerData.nombre}
                                onChange={handleRegisterChange}
                            />
                        </div>
                        <div className="form-group mb-2">
                            <input
                                type="email"
                                className="form-control"
                                placeholder="Correo"
                                name="correo"
                                value={registerData.correo}
                                onChange={handleRegisterChange}
                            />
                        </div>
                        <div className="form-group mb-2">
                            <input
                                type="password"
                                className="form-control"
                                placeholder="Contraseña"
                                name="password"
                                value={registerData.password}
                                onChange={handleRegisterChange}
                            />
                        </div>
                        <div className="form-group mb-2">
                            <input
                                type="password"
                                className="form-control"
                                placeholder="Confirmar Contraseña"
                                name="confirmPassword"
                                value={registerData.confirmPassword}
                                onChange={handleRegisterChange}
                            />
                        </div>
                        <div className="d-grid gap-2">
                            <input
                                type="submit"
                                className="btnSubmit"
                                value="Crear cuenta"
                            />
                        </div>
                    </form>
                </div>
                <div className="col-md-6 login-form-2">
                    <h3>Ingreso</h3>
                    <form onSubmit={handleLoginSubmit}>
                        <div className="form-group mb-2">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Correo"
                                name="correo"
                                value={loginData.correo}
                                onChange={handleLoginChange}
                            />
                        </div>
                        <div className="form-group mb-2">
                            <input
                                type="password"
                                className="form-control"
                                placeholder="Contraseña"
                                name="password"
                                value={loginData.password}
                                onChange={handleLoginChange}
                            />
                        </div>
                        <div className="d-grid gap-2">
                            <input
                                type="submit"
                                className="btnSubmit"
                                value="Iniciar sesión"
                            />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};