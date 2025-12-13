import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { useForm } from "react-hook-form"
import { zodResolver } from '@hookform/resolvers/zod'
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link, useNavigate } from "react-router-dom";
import { Checkbox } from "@/components/ui/checkbox";
import { toast } from "sonner";
import axiosInstance from "@/shared/api/axiosInstance";
import { useAuthStore } from "@/app/store/auth/auth.store";
import type { LoginFormType } from "../types/loginForm.type";
import { LoginSchema } from "../schema/login.schema";
import ForgotPassword from "../../forgot-password/pages/ForgotPassword";
const LoginForm = () => {
    const navigate = useNavigate();
    const {isLoading,setIsLoading} = useAuthStore();

    const form = useForm<LoginFormType>({
        resolver: zodResolver(LoginSchema),
        defaultValues: {
            email: "",
            password: ""
        },
    });
    const onSubmit = async (values: LoginFormType) =>{
        try {

            setIsLoading(true);

            const {data} = await axiosInstance.post("/auth/login",values);

                localStorage.setItem("accessToken",data.accessToken);

                await new Promise(r => setTimeout(r,1200));

                toast.success("Successfully");
                navigate("/");

        } catch (error) {
            console.log(error);
            toast.error("Authentication failed:");
        } finally{
            setIsLoading(false);
        }
    }
    return (
        <>
            <Form {...form}>
                <form className="space-y-4" onSubmit={form.handleSubmit(onSubmit)}>
                    <FormField
                        control={form.control}
                        name="email"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Email</FormLabel>
                                <FormControl>
                                    <Input type="email" placeholder="abdusharipovizzat03@gmail.com" {...field}/>
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
                        <p className="hover:underline">
                            <ForgotPassword/>
                        </p>
                    </div>
                    <Button variant={'secondary'} className="w-full shadow-md" >{isLoading ? "Loading..." : "Sign In Now"}</Button>
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