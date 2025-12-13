import { Button } from "@/components/ui/button"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Label } from "@/components/ui/label"
import NavbarLayout from "@/widgets/navbar/NavbarLayout"
import { zodResolver } from "@hookform/resolvers/zod"
import { Avatar, AvatarFallback, AvatarImage } from "@radix-ui/react-avatar"
import { useForm } from "react-hook-form"
import { UserSchema } from "../model/user.schema"
import { Input } from "@/components/ui/input"
import Footer from "@/widgets/footer/Footer"
import { useAuthStore } from "@/app/store/auth/auth.store"
import { useNavigate } from "react-router-dom"
import { useEffect } from "react"

const EditProfile = () => {
    const { isAuthenticated, user } = useAuthStore();
    const navigate = useNavigate();

    const form = useForm({
        resolver: zodResolver(UserSchema),
        defaultValues: {
            firstName: "",
            lastName: "",
            username: "",
            avatar: ""
        },
    })

    console.log(user);

    useEffect(()=> {
        if(!isAuthenticated){
            navigate("/");
        }
    },[isAuthenticated,navigate]);

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
                                        <AvatarImage src="https://github.com/shadcn.png" className="w-28 rounded-md" />
                                        <AvatarFallback>AI</AvatarFallback>
                                    </Avatar>
                                    <div className="p-4 space-y-4">
                                        <Button variant={'secondary'}>Change avatar</Button>
                                        <Label>JPG, GIF or PNG. 1MB max</Label>
                                    </div>
                                </div>
                                {/* form */}
                                <div className="pt-8">
                                    <Form {...form}>
                                        <form className="space-y-4">
                                            <div className="flex flex-col md:flex-row items-center gap-3">
                                                <FormField
                                                    control={form.control}
                                                    name="firstName"
                                                    render={({ field }) => (
                                                        <FormItem className="w-full">
                                                            <FormLabel>First Name</FormLabel>
                                                            <FormControl>
                                                                <Input type="text" placeholder="Izzatbek" {...field} />
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