import { useState } from "react"
import Navbar from "./Navbar";
import NavbarSheet from "./NavbarSheet";

const NavbarLayout = () => {
    const [open,setOpen] = useState(false);
  return (
    <>
    <Navbar open={open} setOpen={setOpen} />
    <NavbarSheet open={open} setOpen={setOpen} />
    </>
  )
}

export default NavbarLayout