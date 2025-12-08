import zod from 'zod';

export const ForgotPasswordSchema = zod.object({
    email: zod.email("Invalid email format")
});