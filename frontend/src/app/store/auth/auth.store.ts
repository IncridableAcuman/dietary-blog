import type { AuthState } from '@/features/auth/model/auth.schema';
import type { IUser } from '@/features/auth/model/user.types';
import {create} from 'zustand';
export const useAuthStore = create<AuthState>()(set => ({

    isLoading:false,
    isAuthenticated: false,
    user: {} as IUser,
  
    setIsLoading: (v) => set({isLoading:v}),

    setUser: (user) => set({
        isAuthenticated: true,
        user:user
    }),
    clearAuth: () => set({
        isAuthenticated: false,
        user: null
    }),
}));