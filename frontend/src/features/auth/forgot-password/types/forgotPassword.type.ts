import zod from 'zod';
import type { ForgotPasswordSchema } from '../schema/forgotPassword.schema';

export type ForgotPasswordType = zod.infer<typeof ForgotPasswordSchema>;