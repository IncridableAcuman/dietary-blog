import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import Footer from "@/widgets/footer/Footer"
import NavbarLayout from "@/widgets/navbar/NavbarLayout"
import { Star } from "lucide-react"
import { useEffect } from "react"
import { useNavigate } from "react-router-dom"


const Home = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (!localStorage.getItem("accessToken")) {
      navigate("/login");
    }
  }, [navigate]);


return (
  <>
    <div className="w-full min-h-screen bg_image">
      <div className="bg-gray-900 text-white opacity-80">
        <div className="w-full min-h-screen flex flex-col items-center justify-center gap-4 sm:gap-6 md:gap-8 lg:gap-10 pdg">
          <NavbarLayout />
          <div className="space-y-4">
            <Button className="text-xs md:text-sm flex items-center gap-3 justify-center text-center mx-auto text-sky-500">New: AI feature integrated <Star size={15} /></Button>
            <h1 className="text-3xl md:text-6xl font-bold text-center border-spacing-1.5">Your own <span className="text-sky-500">blogging</span> <br /><span>plstform.</span></h1>
          </div>
          <div className="space-y-10">
            <p className="text-center">This is your space to think out loud, to share what matters, and to write without filters. <br /> Whether it's one word or a thousand, your story starts right here.</p>
            <form>
              <div className="flex flex-col md:flex-row items-center gap-3">
                <Input type="text" name="search" id="search" placeholder="Search for blogs" />
                <Button className="w-full md:w-24 cursor-pointer text-gray-900" variant={'outline'}>Search</Button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <Footer />{/*  */}
  </>
)
}

export default Home