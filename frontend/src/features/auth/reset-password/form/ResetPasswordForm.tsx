import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { ResetPasswordSchema } from "../types/resetPassword.type";
import type { ResetPasswordType } from "../schema/resetPassword.schema";
import { toast } from "sonner";
import { useAuthStore } from "@/app/store/auth/auth.store";
import axiosInstance from "@/shared/api/axiosInstance";
import { useLocation, useNavigate } from "react-router-dom";

const ResetPasswordForm = () => {
    const {setIsLoading,isLoading} = useAuthStore();
    const location = useLocation();
    const searchParams = new URLSearchParams(location.search);
    const token = searchParams.get("token");
    const navigate = useNavigate();
    const form = useForm<ResetPasswordType>({
        resolver: zodResolver(ResetPasswordSchema),
        defaultValues: {
            password: "",
        }
    });

    const onSubmit = async (values:ResetPasswordType) =>{
        try {
            setIsLoading(true);
            const {data} = await axiosInstance.put('/auth/reset-password',{
                token,
                password:values.password
            });
            if(data){
               await new Promise(r=>setTimeout(r,1000));
               toast.success(data || "Updated successfully");
               navigate("/login");
            }
        } catch (error) {
            console.log(error);
            toast.error("Reset password failed");
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
                        name="confirmPassword"
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
                    <Button variant={'secondary'} className="w-full shadow-md" >{isLoading ? "Loading..." : "Reset Password"}</Button>
                </form>
            </Form>
        </>
    )
}

export default ResetPasswordForm