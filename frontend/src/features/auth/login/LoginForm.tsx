import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { useForm } from "react-hook-form"
import { zodResolver } from '@hookform/resolvers/zod'
import { UserSchema } from "../model/user.schema";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";
import { Checkbox } from "@/components/ui/checkbox";
const LoginForm = () => {
    const form = useForm({
        resolver: zodResolver(UserSchema),
        defaultValues: {
            email: "",
            password: ""
        },
    });
    return (
        <>
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
                    <div className="flex items-center justify-between text-sm">
                       <div className="flex items-center gap-2">
                         <Checkbox/>
                         Remember me
                       </div>
                        <Link to={"/forgot-password"} className="hover:underline">Forgot Password?</Link>
                    </div>
                    <Button variant={'secondary'} className="w-full shadow-md" >Sign In Now</Button>
                </form>
                <p className="text-center text-xs py-4">or</p>
                <div className="flex items-center justify-center gap-3">
                    <img src="./google.svg" alt="google" className="w-8 cursor-pointer" />
                    <img src="./github.svg" alt="google" className="w-8 cursor-pointer" />
                </div>
                <p className="text-sm py-3">Don't have an account? <Link to={"/register"} className="font-semibold cursor-pointer hover:underline">Sign Up</Link></p>
            </Form>
        </>
    )
}

export default LoginForm