import Login from "@/features/auth/login/Login";
import Register from "@/features/auth/register/Register";
import Home from "@/pages/home/Home";
import BlogList from "@/pages/lists/BlogList";


export const publicRoutes = [
    {path:"/",element:<Home/>},
    {path:"/blogs",element:<BlogList/>},
    {path:"/login",element:<Login/>},
    {path:"/register",element:<Register/>},
    {path:"/forgot-password",element:<Home/>},
]