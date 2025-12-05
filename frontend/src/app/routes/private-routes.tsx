import EditProfile from "@/features/auth/edit/EditProfile";
import BlogDetails from "@/pages/blog/details/BlogDetails";

export const privateRoutes = [
    {path:"/",element:<BlogDetails/>},
    {path:"/profile",element:<EditProfile/>},
]