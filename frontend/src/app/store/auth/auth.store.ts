import type { AuthState } from '@/features/auth/model/auth.schema';
import type { IUser } from '@/features/auth/model/user.types';
import {create} from 'zustand';
export const useAuthStore = create<AuthState>()(set => ({
    isLoading:false,
    isAuthenticated:!!localStorage.getItem("accessToken"),
    user: {} as IUser,
    setIsAuthenticed:(bool:boolean) => set(state => ({...state,isAuthenticated:bool})),
    setIsLoading: (bool:boolean) => set({isLoading:bool}),
    setUser:(user:IUser) => set(state => ({...state,user:user})),
    login: (token:string) =>{
        localStorage.setItem("accessToken",token);
        set({isAuthenticated:true});
    },
    logout:()=>{
        localStorage.removeItem("accessToken");
        set({isAuthenticated:false});
    }
}));