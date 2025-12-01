import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form"
import { UserSchema } from "../model/user.schema";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

const ResetPasswordForm = () => {
    const form = useForm({
        resolver:zodResolver(UserSchema),
        defaultValues:{
            password:"",         
        }
    });
  return (
    <>
                <Form {...form}>
                <form className="space-y-4">
                    <FormField
                        control={form.control}
                        name="password"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Password</FormLabel>
                                <FormControl>
                                    <Input type="password" placeholder="********" {...field} />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    <FormField
                        control={form.control}
                        name="password"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Confirm Password</FormLabel>
                                <FormControl>
                                    <Input type="password" placeholder="********" {...field} />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    <Button variant={'secondary'} className="w-full shadow-md" >Sign In Now</Button>
                </form>
            </Form>
    </>
  )
}

export default ResetPasswordForm