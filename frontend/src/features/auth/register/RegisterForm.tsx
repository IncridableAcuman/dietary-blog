import { useForm } from "react-hook-form"
import { zodResolver } from '@hookform/resolvers/zod'
import { UserSchema } from "../model/user.schema";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Link } from "react-router-dom";

const RegisterForm = () => {
  const form = useForm({
    resolver: zodResolver(UserSchema),
    defaultValues: {
      firstName: "",
      lastName: "",
      username: "",
      email: "",
      password: ""
    },
  });
  return (
    <>
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
                  <FormMessage/>
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
                   <FormMessage/>
                </FormItem>
              )}
            />
          </div>
          <FormField
            control={form.control}
            name="username"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Username</FormLabel>
                <FormControl>
                  <Input type="text" placeholder="IncridableAcuman" {...field} />
                </FormControl>
                 <FormMessage/>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="email"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Email</FormLabel>
                <FormControl>
                  <Input type="email" placeholder="abdusharipovizzat03@gmail.com" {...field} />
                </FormControl>
                 <FormMessage/>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="password"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Password</FormLabel>
                <FormControl>
                  <Input type="password" placeholder="********" {...field}  />
                </FormControl>
                <FormMessage/>
              </FormItem>
            )}
          />
          <Button variant={'secondary'} className="w-full shadow-md" >Sign Up Now</Button>
        </form>
        <p className="text-center text-xs py-4">or</p>
        <div className="flex items-center justify-center gap-3">
          <img src="./google.svg" alt="google" className="w-8 cursor-pointer" />
          <img src="./github.svg" alt="google" className="w-8 cursor-pointer" />
        </div>
        <p className="text-sm py-3">Already have an account? <Link to={"/login"} className="font-semibold cursor-pointer hover:underline">Sign In</Link></p>
      </Form>
    </>
  )
}

export default RegisterForm