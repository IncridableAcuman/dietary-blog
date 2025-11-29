import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { publicRoutes } from "./public-routes";

const router = createBrowserRouter([
    ...publicRoutes
]);

export const AppRouter = () =>{
    return <RouterProvider router={router} />
}