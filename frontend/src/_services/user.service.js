import { authHeader } from '../_helpers';
import axios from 'axios';

export const userService = {
    login,
    logout,
    register,
    getAll,
    getById,
    // update,
    // delete: _delete
};

axios.defaults.withCredentials = true
function login(username, password) {
    return axios.post(`http://localhost:8080/login`, `username=${username}&password=${password}`)
        .then(handleResponse)
        .then(user => {
            localStorage.setItem('user', JSON.stringify(user));
            return user;
        })
        .catch((error) => {
            // Error
            if (error.response) {
                console.log(error.response.data);
                console.log(error.response.status);
                console.log(error.response.headers);
            } else if (error.request) {
                console.log(error.request);
            } else {
                // Something happened in setting up the request that triggered an Error
                console.log('Error', error.message);
            }
            console.log(error.config);
        });
}


function logout() {
    localStorage.removeItem('user');
    return axios.get(`http://localhost:8080/logout`)
}

function getAll() {
    return axios.get(`http://localhost:8080/accounts`)
    .then(handleResponse)
    .then(response => {
        console.log(response);
    })
}

function getById(id) {
    const requestOptions = {
        method: 'GET',
        headers: authHeader()
    };

    return fetch(`/users/${id}`, requestOptions).then(handleResponse);
}

function register(user) {
    return axios.post(`http://localhost:8080/register`, user).then(handleResponse);
}

// function update(user) {
//     const requestOptions = {
//         method: 'PUT',
//         headers: { ...authHeader(), 'Content-Type': 'application/json' },
//         body: JSON.stringify(user)
//     };

//     return fetch(`/users/${user.id}`, requestOptions).then(handleResponse);;
// }

// function _delete(id) {
//     const requestOptions = {
//         method: 'DELETE',
//         headers: authHeader()
//     };

//     return fetch(`/users/${id}`, requestOptions).then(handleResponse);
// }

function handleResponse(response) {
    console.log(response)
    if (response.status === 401) {
        // auto logout if 401 response returned from api
        logout();
    }
    return response;
}
