import { Sheet, SheetContent, SheetFooter, SheetTrigger } from "@/components/ui/sheet"
import { Building, Menu } from "lucide-react"
import { Link } from "react-router-dom"

const SidebarSheet = ({open,setOpen}:{open:boolean,setOpen:(value:boolean)=>void}) => {
    return (
        <div>
            <Sheet open={open} onOpenChange={setOpen}>
                <SheetTrigger asChild>
                    <Menu onClick={()=>setOpen(true)} className="cursor-pointer text-gray-500 hover:text-gray-900 transition duration-300" />
                </SheetTrigger>
                <SheetContent className="pt-12">
                    <div className="flex flex-col items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10 p-3">
                        <Link to={"/"} className="hover:bg-gray-800 hover:text-white transition duration-300 py-2.5 px-3 rounded-md w-full mx-auto text-center" onClick={()=>setOpen(false)}>Home</Link>
                        <Link to={"/"} className="hover:bg-gray-800 hover:text-white transition duration-300 py-2.5 px-3 w-full mx-auto text-center rounded-md" onClick={()=>setOpen(false)}>About</Link>
                        <Link to={"/"} className="hover:bg-gray-800 hover:text-white transition duration-300 py-2.5 px-3 rounded-md w-full mx-auto text-center" onClick={()=>setOpen(false)}>Blogs</Link>
                    </div>
                    <SheetFooter className="text-center">
                        <div className="flex items-center justify-center gap-3 text-gray-500">
                            <Building />
                            <p>Uzbekistan</p>
                        </div>
                   <p className="text-sm">Created by: Izzatbek Abdusharipov</p>
                    </SheetFooter>
                </SheetContent>
            </Sheet>
        </div>
    )
}

export default SidebarSheet