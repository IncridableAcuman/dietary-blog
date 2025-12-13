import EditProfile from "@/features/auth/edit/EditProfile";
import BlogDetails from "@/pages/blog/details/BlogDetails";
import About from "@/pages/about/About";
import BlogList from "@/pages/blog/lists/BlogList";
import Home from "@/pages/home/Home";
import PrivateRoutes from "./PrivateRoutes";

export const privateRoutes = [
    {
        path: "/", element:
            <PrivateRoutes>
                <Home />
            </PrivateRoutes>
    },
    {
        path: "/about", element:
            <PrivateRoutes>
                <About />
            </PrivateRoutes>
    },
    {
        path: "/blogs", element:
            <PrivateRoutes>
                <BlogList />
            </PrivateRoutes>
    },
    {
        path: "/blog/:id", element: (
            <PrivateRoutes>
                <BlogDetails />
            </PrivateRoutes>
        )
    },
    {
        path: "/profile", element: (
            <EditProfile />
        )
    },
]