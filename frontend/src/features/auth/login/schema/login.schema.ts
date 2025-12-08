import zod from 'zod';
export const LoginSchema = zod.object({
        email:zod.email(),
        password:zod.string().min(8,"Password is too short").max(255,"Password is too long"),
});