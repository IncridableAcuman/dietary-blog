import NavbarLayout from "@/widgets/navbar/NavbarLayout"
import LoginForm from "./LoginForm"

const Login = () => {
  return (
   <div className="w-full h-screen bg_image">
      <div className="bg-gray-900 text-white w-full h-screen flex flex-col items-center justify-center opacity-90">
          <NavbarLayout/>
        <div className="bg-black text-white w-full max-w-md md:max-w-lg shadow-md rounded-md p-6">
          <h1 className='text-lg font-semiboldx md:text-2xl  py-4'>Sign In Now</h1>
          <LoginForm/>
        </div>
      </div>
    </div>
  )
}

export default Login