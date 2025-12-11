import type { JSX } from "react";
import { useAuthStore } from "../store/auth/auth.store";
import { Navigate, useLocation } from "react-router-dom";

export default function PrivateRoutes({children}:{children:JSX.Element}){
    const isAuthenticated = useAuthStore(store => store.isAuthenticated);
    const location = useLocation();
    if(!isAuthenticated){
        <Navigate to={"/login"} state={{from:location}} replace />
    } 
    return children;
}