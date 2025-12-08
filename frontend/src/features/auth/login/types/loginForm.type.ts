import { LoginSchema } from '../schema/login.schema';
import {z} from 'zod';

export type LoginFormType = z.infer<typeof LoginSchema>;