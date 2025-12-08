import type { IUser } from "./user.types";

export interface AuthState{
    isLoading:boolean,
    isAuthenticated: boolean,
    user:IUser,
    setIsAuthenticed: (bool:boolean)=>void
    setIsLoading:(bool:boolean)=>void,
    setUser:(user:IUser)=>void,
    login: (token:string)=>void,
    logout:()=>void,
}