import axios from 'axios';

export const apiClient = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json',
    },
});

// 取得授權 headers（動態附加 token）
export const getAuthHeaders = () => {
    const token = localStorage.getItem('token');
    const headers = { 'Content-Type': 'application/json' };
    if (token) {
        headers['Authorization'] = `Bearer ${token}`;
    }
    return { headers };
};

// API 方法
export const login = (username, password) => {
    return apiClient.post('/users/login', { username, password });
};

export const fetchProducts = () => {
    return apiClient.get('/products');
};

export const getProducts = () => apiClient.get('/products');

export const createProduct = (productData) =>
    apiClient.post('/products', productData, getAuthHeaders());

export const updateProduct = (productID, productData) =>
    apiClient.put(`/products/${productID}`, productData, getAuthHeaders());

export const deleteProductById = (productID) =>
    apiClient.delete(`/products/${productID}`, getAuthHeaders());

export const createOrder = (orderPayload) => {
    return apiClient.post('/orders/create', orderPayload, getAuthHeaders());
};
export const fetchOrdersByUser = (userId) => {
    return apiClient.get(`/orders/order`, {
        params: { userId }
    }, getAuthHeaders());
};
export const registerUser = (formData) => {
    return apiClient.post('/users/register', formData);
};