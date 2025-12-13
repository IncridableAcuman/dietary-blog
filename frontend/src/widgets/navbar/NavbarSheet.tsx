import { useAuthStore } from "@/app/store/auth/auth.store"
import { Button } from "@/components/ui/button"
import { Sheet, SheetClose, SheetContent, SheetFooter, SheetHeader, SheetTitle, SheetTrigger } from "@/components/ui/sheet"
import axiosInstance from "@/shared/api/axiosInstance"
import { Avatar, AvatarFallback, AvatarImage } from "@radix-ui/react-avatar"
import { Pen, UserRound } from "lucide-react"
import { useNavigate } from "react-router-dom"
import { toast } from "sonner"

const NavbarSheet = ({ open, setOpen }: { open: boolean, setOpen: (value: boolean) => void }) => {
    const navigate = useNavigate();
    const { user } = useAuthStore();


    const onSubmit = async () => {

        try {

            await axiosInstance.post('/auth/logout');

            localStorage.removeItem("accessToken");

            await new Promise(r => setTimeout(r, 1000));

            toast.success("Successfully logged out");
            navigate("/login");

        } finally {
            localStorage.removeItem("accessToken");
            navigate("/login");
        }
    }

    return (
        <Sheet open={open} onOpenChange={setOpen} >
            <SheetTrigger asChild>
            </SheetTrigger>
            <SheetContent className="opacity-95">
                <SheetHeader className="flex flex-col items-center justify-center pt-10">
                    <Avatar
                        className=""
                    >
                        <AvatarImage src="https://github.com/shadcn.png" className="w-14 border-4 rounded-full cursor-pointer bg-linear-to-tr from-green-400 via-emerald-500/30 to-green-700
                         shadow-md shadow-emerald-500/30 hover:shadow-emerald-500/60 transition duration-300" />
                        <AvatarFallback>AI</AvatarFallback>
                    </Avatar>
                    <div className="flex items-center gap-3">
                        <SheetTitle className="text-center">User Profile</SheetTitle>
                        <Pen size={15} onClick={() => navigate("/profile")} className="cursor-pointer text-gray-500 hover:text-gray-700 transition duration-300" />
                    </div>
                    <p className="border-b-2 pb-3 w-full text-center border-dashed">{user?.email ?? 'example@gmail.com'}</p>
                </SheetHeader>
                <div className="py-4 px-5">
                    <div className="space-y-4">
                        <div className="py-2 flex items-center">
                            <div className="flex items-center gap-3 text-sm e">
                                <UserRound size={20} />
                                First Name:
                            </div>
                            <p className="pl-8 font-bold text-gray-600">{user?.firstName ?? 'John'}</p>
                        </div>
                        <div className="flex items-center py-2">
                            <div className="flex items-center gap-3 text-sm ">
                                <UserRound size={20} />
                                Last Name:
                            </div>
                            <p className="pl-8 font-bold text-gray-600">{user?.lastName ?? 'Doe'}</p>
                        </div>
                        <div className="py-2 flex items-center">
                            <div className="flex items-center gap-3 text-sm">
                                <UserRound size={20} />
                                Username
                            </div>
                            <p className="pl-8 font-bold text-gray-600">{user?.username ?? 'JohnDoe'}</p>
                        </div>

                    </div>
                </div>
                <SheetFooter>
                    <SheetClose asChild>
                        <Button onClick={() => onSubmit()}>Sign Out</Button>
                    </SheetClose>
                </SheetFooter>
            </SheetContent>
        </Sheet>
    )
}

export default NavbarSheet