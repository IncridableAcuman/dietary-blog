import type { AuthState } from '@/features/auth/model/auth.schema';
import type { IUser } from '@/features/auth/model/user.types';
import {create} from 'zustand';
export const useAuthStore = create<AuthState>()(set => ({

    isLoading:false,
    isAuthenticated:false,
    user: null,

    setIsLoading: (v) => set({isLoading:v}),
    setUser:(user:IUser) => set(state => ({...state,user:user})),
    login: (user:IUser) => set({
        isAuthenticated:true,
        user:user
    }),
    logout:()=> set({
        isAuthenticated:false,
        user:null
    }),
}));