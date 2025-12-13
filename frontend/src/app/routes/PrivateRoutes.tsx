import type { JSX } from "react";
import { Navigate, useLocation } from "react-router-dom";

export default function PrivateRoutes({children}:{children:JSX.Element}){
    const location = useLocation();
    if(!localStorage.getItem("accessToken")){
        return <Navigate to={"/login"} state={{from:location}} replace />;
    } 
    return children;
}