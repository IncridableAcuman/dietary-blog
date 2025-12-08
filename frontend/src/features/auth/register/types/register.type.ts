import zod from 'zod';
import type { RegisterSchema } from '../schema/register.schema';
export type RegisterSchemaType = zod.infer<typeof RegisterSchema>;