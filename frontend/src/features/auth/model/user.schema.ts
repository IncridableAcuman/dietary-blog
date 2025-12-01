import zod from 'zod';

export const UserSchema = zod.object({
    id:zod.number(),
    firstName:zod.string().min(3,"FirstName is too short").max(100,"FirstName is too long"),
    lastName:zod.string().min(3,"LastName is too short").max(100,"LastName is too long"),
    username:zod.string().min(3,"Username is too short").max(100,"Username is too long"),
    email:zod.email(),
    password:zod.string().min(8,"Password is too short").max(255,"Password is too long"),
    role:zod.string(),
    avatar:zod.string()
})