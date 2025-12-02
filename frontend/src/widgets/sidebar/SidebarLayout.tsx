import { useState } from "react"
import SidebarSheet from "./SidebarSheet"

const SidebarLayout = () => {
    const [open,setOpen]=useState(false);
  return (
    <>
    <SidebarSheet open={open} setOpen={setOpen} />
    </>
  )
}

export default SidebarLayout