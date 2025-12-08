import Footer from "@/widgets/footer/Footer"
import NavbarLayout from "@/widgets/navbar/NavbarLayout"

const BlogList = () => {
  return (
    <>
      <div className="w-full min-h-screen bg_image">
        <div className="bg-gray-900 text-white opacity-80">
          <div className="w-full min-h-screen">
            <NavbarLayout />
          </div>
        </div>
      </div>
      <Footer />
    </>
  )
}

export default BlogList