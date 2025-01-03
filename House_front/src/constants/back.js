
export const LOCAL_HOST = 'http://localhost'
export const LOCAL_HOST_NOTIF = 'http://localhost'


export const GET_DEVICES_LOGIN =(login) => `${LOCAL_HOST}/api/devices/myDevices/${login}`;
export const GET_CONFIG_LOGIN =(login) => `${LOCAL_HOST}/api/devices/configurations/${login}`;
export const ADD_CONFIGURATION = (deviceId) => `${LOCAL_HOST}/api/configurations/add/${deviceId}`;
export const UPDATE_CONFIGURATION = (deviceId) => `${LOCAL_HOST}/api/configurations/update/${deviceId}`;
export const LOGIN_USER = LOCAL_HOST + '/api/users/loginUser';


export const loginUser = async (login, password) => {
    try {
        const response = await fetch(LOGIN_USER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                login: login,
                password: password
            }),
        });
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(`Error: ${errorText}`);
        }
        const data = await response.json();
        console.log('Login response data:', data)
        if (data && data.id) {
            sessionStorage.setItem('userId', data.id);
            console.log('User ID stored in sessionStorage:', data.id);
        } else {
            console.error('userId not found in response');
        }return data;
    } catch (error) {
        console.error('Login failed:', error.message);
        throw error;
    }
};

//////////////////////////////////////////////////////////////////////////////////////////////////////

export const GET_NOTIFICATIONS = (userId) => `${LOCAL_HOST_NOTIF}/notifications/all/${userId}`;

export const fetchNotifications = async (userId) => {
    try {
        const response = await fetch(GET_NOTIFICATIONS(userId), {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            throw new Error('Failed to fetch notifications');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching notifications:', error);
        throw error;
    }
};