import {create} from 'zustand';
import type { AuthState } from './auth.schema';
import type { IUser } from './user.types';
export const useAuthStore = create<AuthState>()(set => ({
    isLoading:false,
    user: {} as IUser,
    setIsLoading: (bool:boolean) => set(state => ({...state,isLoading:bool})),
    setUser:(user:IUser) => set(state => ({...state,user:user})),
}));