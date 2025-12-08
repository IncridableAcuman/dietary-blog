import zod from 'zod';

export const ResetPasswordSchema = zod.object({
    password: zod.string().min(8, "Password is too short").max(255, "Password is too long"),
    confirmPassword: zod.string().min(8, "Password is too short").max(255, "Password is too long"),
})
.refine((data) => data.password === data.confirmPassword,{
    message: "Password don't match",
    path:['confirmPassword']
})