import { LoginSchema } from './login.schema';
import {z} from 'zod';

export type LoginFormType = z.infer<typeof LoginSchema>;