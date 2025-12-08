import ForgotPassword from "@/features/auth/forgot-password/ForgotPassword";
import Login from "@/features/auth/login/pages/Login";
import Register from "@/features/auth/register/pages/Register";
import About from "@/pages/about/About";
import BlogList from "@/pages/blog/lists/BlogList";
import Home from "@/pages/home/Home";


export const publicRoutes = [
    {path:"/",element:<Home/>},
    {path:"/about",element:<About/>},
    {path:"/blogs",element:<BlogList/>},
    {path:"/login",element:<Login/>},
    {path:"/register",element:<Register/>},
    {path:"/forgot-password",element:<ForgotPassword/>},
]