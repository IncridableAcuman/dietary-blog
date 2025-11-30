import BlogDetails from "@/pages/details/BlogDetails";
import Profile from "@/pages/profile/Profile";

export const privateRoutes = [
    {path:"/",element:<BlogDetails/>},
    {path:"/profile/:id",element:<Profile/>},
]