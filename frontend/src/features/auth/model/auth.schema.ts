import type { IUser } from "./user.types";

export interface AuthState{

    isLoading:boolean,
    isAuthenticated: boolean,
    user:IUser | null,
    setIsAuthenticated:(bool:boolean)=>void,
    setIsLoading:(bool:boolean)=>void,
    setUser:(user:IUser)=>void,
}