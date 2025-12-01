import { Button } from "@/components/ui/button";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form"
import { UserSchema } from "../model/user.schema";

const ForgotPasswordForm = () => {
    const form = useForm({
        resolver:zodResolver(UserSchema),
        defaultValues:{
            email:""
        },
    });
  return (
    <Form {...form}>
                <form className="space-y-4">
                    <FormField
                        control={form.control}
                        name="email"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Email</FormLabel>
                                <FormControl>
                                    <Input type="email" placeholder="abdusharipovizzat03@gmail.com" {...field} />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    <Button variant={'secondary'} className="w-full shadow-md" >Sign In Now</Button>
                </form>
            </Form>
  )
}

export default ForgotPasswordForm