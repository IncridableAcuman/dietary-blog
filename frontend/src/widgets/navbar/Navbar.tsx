import { Avatar, AvatarFallback, AvatarImage } from "@radix-ui/react-avatar"
import { Link } from "react-router-dom"
import SidebarLayout from "../sidebar/SidebarLayout"

const Navbar = ({ setOpen }: { open: boolean, setOpen: (value: boolean) => void }) => {
  return (
    <div className="fixed top-0 left-0 w-full flex items-center justify-between gap-4 py-4 px-4 sm:px-6 md:px-8 lg:px-10 bg-gray-900 opacity-80">
      <Link to={"/"} className="text-lg font-semibold md:text-xl">Diet<span className="text-green-500">BP</span></Link>
      <div className="hidden md:flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
        <Link to={"/"} className="hover:bg-green-500 hover:text-white transition duration-300 py-2.5 px-3 rounded-md">Home</Link>
        <Link to={"/"} className="hover:bg-green-500 hover:text-white transition duration-300 py-2.5 px-3 rounded-md">About</Link>
        <Link to={"/"} className="hover:bg-green-500 hover:text-white transition duration-300 py-2.5 px-3 rounded-md">Blogs</Link>
      </div>
      <div className="flex items-center gap-4 sm:gap-6 md:gap-8 lg:gap-10">
        <Avatar
          className="cursor-pointer rounded-full p-0.5 bg-gradient-t-tr from-green-400 via-emerald-500 to-green-700
       shadow-lg shadow-emerald-500/30 hover:shadow-emerald-300/60 transition duration-300 "
          onClick={() => setOpen(true)}
        >
          <AvatarImage src="https://github.com/shadcn.png" className="w-8 rounded-full" />
          <AvatarFallback>AI</AvatarFallback>
        </Avatar>
        <div className="block md:hidden">
          <SidebarLayout />
        </div>
      </div>
    </div>
  )
}

export default Navbar