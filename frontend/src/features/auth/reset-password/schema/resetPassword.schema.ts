import zod from 'zod';
import type { ResetPasswordSchema } from '../types/resetPassword.type';

export type ResetPasswordType = zod.infer<typeof ResetPasswordSchema>;