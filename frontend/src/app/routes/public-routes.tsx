import ForgotPassword from "@/features/auth/forgot-password/pages/ForgotPassword";
import Login from "@/features/auth/login/pages/Login";
import Register from "@/features/auth/register/pages/Register";
import ResetPassword from "@/features/auth/reset-password/pages/ResetPassword";


export const publicRoutes = [
    {path:"/login",element:<Login/>},
    {path:"/register",element:<Register/>},
    {path:"/forgot-password",element:<ForgotPassword/>},
    {path: "/reset-password",element:<ResetPassword/>}
]