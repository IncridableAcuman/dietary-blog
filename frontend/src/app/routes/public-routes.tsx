import Home from "@/pages/home/Home";
import BlogList from "@/pages/lists/BlogList";


export const publicRoutes = [
    {path:"/",element:<Home/>},
    {path:"/blogs",element:<BlogList/>},
]