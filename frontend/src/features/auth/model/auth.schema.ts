import type { IUser } from "./user.types";

export interface AuthState{
    isLoading:boolean,
    user:IUser,
    setIsLoading:(bool:boolean)=>void,
    setUser:(user:IUser)=>void
}