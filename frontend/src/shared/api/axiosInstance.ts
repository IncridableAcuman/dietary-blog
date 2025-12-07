import type { IUser } from './../../features/auth/model/user.types';
import axios from 'axios';

const axiosInstance = axios.create({
    withCredentials: true,
    baseURL: "http://localhost:8080/api"
});

axiosInstance.interceptors.request.use(
    config => {
        const token = localStorage.getItem("accessToken");
        if (token && config.url?.includes("/auth/refresh")) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
    config=>config,
    async (error)=>{
        const originalRequest = error.config;

        if(originalRequest.url?.includes("/auth/refresh")){
            localStorage.removeItem("accessToken");
            window.location.href="/login";
            return Promise.reject(error);
        }

        if(error.response.status === 401 && !originalRequest._retry){
            originalRequest._retry = true;
            try {
                const {data}:{data:IUser} = await axiosInstance.get("/auth/refresh");
                localStorage.setItem("accessToken",data.accessToken);
                originalRequest.headers['Authorization']=`Bearer ${data.accessToken}`;
                return axiosInstance.request(originalRequest);

            } catch (error) {
                console.log(error);
                localStorage.removeItem("accessToken");
                window.location.href="/login";
            }
        }
        return Promise.reject(error)
    },
);

export default axiosInstance;