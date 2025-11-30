import BlogDetails from "@/pages/blog/details/BlogDetails";
import Profile from "@/pages/user/profile/Profile";

export const privateRoutes = [
    {path:"/",element:<BlogDetails/>},
    {path:"/profile/:id",element:<Profile/>},
]