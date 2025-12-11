import type { IUser } from "./user.types";

export interface AuthState{

    isLoading:boolean,
    isAuthenticated: boolean,
    user:IUser | null,

    setIsLoading:(bool:boolean)=>void,
    setUser:(user:IUser)=>void,
    login: (user:IUser)=>void,
    logout:()=>void,
}