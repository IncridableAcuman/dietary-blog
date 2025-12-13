import  zod  from 'zod';

export const EditProfileSchema = zod.object({
    firstName: zod.string().min(3).max(100),
    lastName: zod.string().min(3).max(100),
    username: zod.string().min(3).max(100),
    avatar: zod.instanceof(File).or(zod.null()),
});