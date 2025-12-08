import zod from 'zod';

export const RegisterSchema = zod.object({
    firstName: zod.string().min(3,"First Name too short").max(100,"First Name is too long"),
    lastName: zod.string().min(3,"Last Name too short").max(100,"Last Name is too long"),
    username: zod.string().min(3," Username too short").max(100,"Username is too long"),
    email: zod.email("Invalid email format."),
    password: zod.string().min(8,"Password is too short").max(255,"Password is too long")
});