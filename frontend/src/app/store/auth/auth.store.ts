import type { AuthState } from '@/features/auth/model/auth.schema';
import type { IUser } from '@/features/auth/model/user.types';
import {create} from 'zustand';
export const useAuthStore = create<AuthState>()(set => ({

    isLoading:false,
    isAuthenticated:false,
    user: {} as IUser | null,
    setIsAuthenticated: (v) => set({isAuthenticated:v}),
    setIsLoading: (v) => set({isLoading:v}),
    setUser:(user:IUser) => set(state => ({...state,user:user})),
}));