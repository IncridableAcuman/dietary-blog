import { Button } from "@/components/ui/button";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form"
import { Dialog, DialogClose, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import { ForgotPasswordSchema } from "../schema/forgotPassword.schema";
import type { ForgotPasswordType } from "../types/forgotPassword.type";
import { useAuthStore } from "@/app/store/auth/auth.store";
import { toast } from "sonner";
import axiosInstance from "@/shared/api/axiosInstance";

const ForgotPasswordForm = () => {
    const {isLoading,setIsLoading} = useAuthStore();
    const form = useForm<ForgotPasswordType>({
        resolver: zodResolver(ForgotPasswordSchema),
        defaultValues: {
            email: ""
        },
    });

    const onSubmit = async (values:ForgotPasswordType) =>{
        try {
            setIsLoading(true);
            const {data} = await axiosInstance.post("/auth/forgot-password",values);
            if(data){
                await new Promise(r=>setTimeout(r,1000));
                toast.success(data || "Link sent to email");
            }
        } catch (error) {
            console.log(error);
            toast.error("Link not sent to email");
        } finally{
            setIsLoading(false);
        }
    }

    return (
        <Dialog>
            <DialogTrigger>Forgot Password</DialogTrigger>
            <DialogContent>
                <DialogHeader>
                    <DialogTitle>Forgot Password</DialogTitle>
                    <DialogDescription>Reset password link sent to email.Enter your email</DialogDescription>
                </DialogHeader>
                <Form {...form}>
                    <form className="space-y-4" onSubmit={form.handleSubmit(onSubmit)}>
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
                        <Button className="w-full" >{isLoading ? "Loading..." : "Send to email"}</Button>
                    </form>
                </Form>
                <DialogFooter>
                    <DialogClose asChild>
                        <Button className="w-full">Cancel</Button>
                    </DialogClose>
                </DialogFooter>
            </DialogContent>
        </Dialog>
    )
}

export default ForgotPasswordForm