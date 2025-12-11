import type { JSX } from "react";
import { useAuthStore } from "../store/auth/auth.store";
import { Navigate } from "react-router-dom";

export default function PrivateRoutes({children}:{children:JSX.Element}){
    const isAuthenticated = useAuthStore(store => store.isAuthenticated);
    if(!isAuthenticated){
        <Navigate to={"/login"} replace />
    } 
    return children;
}