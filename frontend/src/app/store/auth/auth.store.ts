import type { AuthState } from '@/features/auth/model/auth.schema';
import type { IUser } from '@/features/auth/model/user.types';
import {create} from 'zustand';
export const useAuthStore = create<AuthState>()(set => ({
    isLoading:false,
    user: {} as IUser,
    setIsLoading: (bool:boolean) => set(state => ({...state,isLoading:bool})),
    setUser:(user:IUser) => set(state => ({...state,user:user})),
}));