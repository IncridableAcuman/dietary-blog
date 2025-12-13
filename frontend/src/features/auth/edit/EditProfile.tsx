import { Button } from "@/components/ui/button"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import NavbarLayout from "@/widgets/navbar/NavbarLayout"
import { zodResolver } from "@hookform/resolvers/zod"
import { Avatar, AvatarFallback, AvatarImage } from "@radix-ui/react-avatar"
import { useForm } from "react-hook-form"
import { Input } from "@/components/ui/input"
import Footer from "@/widgets/footer/Footer"
import { EditProfileSchema } from "./schema/edit.schema"
import type { EditProfileFormType } from "./types/edit.type"
import axiosInstance from "@/shared/api/axiosInstance"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import { toast } from "sonner"
const EditProfile = () => {
    const [avatarPreview, setAvatarPreview] = useState<string | null>(null);
    const navigate = useNavigate();
    const id = localStorage.getItem("id");

    const form = useForm({
        resolver: zodResolver(EditProfileSchema),
        defaultValues: {
            firstName: "",
            lastName: "",
            username: "",
            avatar: null,
        },
    });

    const onSubmit = async (data: EditProfileFormType) => {
        try {
            const formData = new FormData();
            formData.append('firstName', data.firstName);
            formData.append('lastName', data.lastName);
            formData.append('username', data.username);
            if (data?.avatar) {
                formData.append('avatar', data.avatar);
            }
            const response = await axiosInstance.patch(`/profile/edit/${id}`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data",
                    "Authorization": `Bearer ${localStorage.getItem("accessToken")}`
                }
            });
            toast.success("Profile updated successfully");
            console.log(response.data);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(()=>{
        if(!localStorage.getItem("accessToken")){
            navigate("/login");
        }
    },[navigate]);



    return (
        <>
            <div className="w-full min-h-screen bg_image">
                <div className="bg-gray-900 text-white opacity-80">
                    <div className="w-full min-h-screen pdg pt-24">
                        <NavbarLayout />

                        <div className="pt-24 flex flex-col md:flex-row items-center justify-between gap-4 sm:gap-6 md:gap-8 lg:gap-10">
                            <div className="space-y-2">
                                <h1 className="text-lg font-semibold md:text-xl">Personal Information</h1>
                                <span>Use a permanent address where you can receive mail.</span>
                            </div>
                            <div className="w-full max-w-lg">
                                <div className="flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
                                    <Avatar
                                        className=""
                                    >
                                        <AvatarImage src={avatarPreview || "https://github.com/shadcn.png"} className="w-28 rounded-md" />
                                        <AvatarFallback>AI</AvatarFallback>
                                    </Avatar>
                                </div>
                                {/* form */}
                                <div className="pt-8">
                                    <Form {...form}>
                                        <form className="space-y-4" onSubmit={form.handleSubmit(onSubmit)}>
                                            <FormField
                                                control={form.control}
                                                name="avatar"
                                                render={({ field }) => (
                                                    <FormItem>
                                                        <FormLabel>Avatar URL</FormLabel>
                                                        <FormControl>
                                                           <Button variant={'outline'} className="cursor-pointer">
                                                             <Input type="file" accept="/image/**" className="cursor-pointer" onChange={(e)=>{
                                                                const file = e.target.files?.[0];
                                                                field.onChange(file);
                                                                if(file) setAvatarPreview(URL.createObjectURL(file));
                                                             }} />
                                                           </Button>
                                                        </FormControl>
                                                        <FormMessage />
                                                    </FormItem>
                                                )}
                                            />
                                            <div className="flex flex-col md:flex-row items-center gap-3">
                                                <FormField
                                                    control={form.control}
                                                    name="firstName"
                                                    render={({ field }) => (
                                                        <FormItem className="w-full">
                                                            <FormLabel>First Name</FormLabel>
                                                            <FormControl>
                                                                <Input type="text" placeholder="Izzatbek"  {...field} />
                                                            </FormControl>
                                                            <FormMessage />
                                                        </FormItem>
                                                    )}
                                                />
                                                <FormField
                                                    control={form.control}
                                                    name="lastName"
                                                    render={({ field }) => (
                                                        <FormItem className="w-full">
                                                            <FormLabel>Last Name</FormLabel>
                                                            <FormControl>
                                                                <Input type="text" placeholder="Abdusharipov" {...field} />
                                                            </FormControl>
                                                            <FormMessage />
                                                        </FormItem>
                                                    )}
                                                />
                                            </div>
                                            <FormField
                                                control={form.control}
                                                name="username"
                                                render={({ field }) => (
                                                    <FormItem className="w-full">
                                                        <FormLabel>Username</FormLabel>
                                                        <FormControl>
                                                            <Input type="text" placeholder="IncridableAcuman" {...field} />
                                                        </FormControl>
                                                        <FormMessage />
                                                    </FormItem>
                                                )}
                                            />
                                            <Button className="cursor-pointer" variant={'secondary'}>Update Profile</Button>
                                        </form>
                                    </Form>
                                </div>
                                {/* form */}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    )
}

export default EditProfile