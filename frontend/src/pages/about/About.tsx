import Contact from "@/widgets/contact/Contact"
import NavbarLayout from "@/widgets/navbar/NavbarLayout"

const About = () => {
  const stats = [
  { id: 1, name: 'Transactions every 24 hours', value: '44 million' },
  { id: 2, name: 'Assets under holding', value: '$119 trillion' },
  { id: 3, name: 'New users annually', value: '46,000' },
]
  return (
    <div className="w-full min-h-screen pdg">
      <NavbarLayout/>
      <div className="">
        <div className="flex flex-col md:flex-row items-center justify-between gap-4 sm:gap-6 md:gap-8 lg:gap-10 pt-24">
          <img src="pexels-thelazyartist-1332313.jpg" alt="image" className="w-full min-w-[50%] rounded-lg" />
          <div className="space-y-4">
            <h1 className="text-xl font-bold">Dietary Blog Platform</h1>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
               Deleniti necessitatibus porro exercitationem nesciunt sequi tenetur,
               possimus laudantium aspernatur quos repudiandae error natus qui assumenda perspiciatis delectus optio ex atque id.</p>
               <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
               Deleniti necessitatibus porro exercitationem nesciunt sequi tenetur,
               possimus laudantium aspernatur quos repudiandae error natus qui assumenda perspiciatis delectus optio ex atque id.</p>
               <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
               Deleniti necessitatibus porro exercitationem nesciunt sequi tenetur,
               possimus laudantium aspernatur quos repudiandae error natus qui assumenda perspiciatis delectus optio ex atque id.</p>
               <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
               Deleniti necessitatibus porro exercitationem nesciunt sequi tenetur,
               possimus laudantium aspernatur quos repudiandae error natus qui assumenda perspiciatis delectus optio ex atque id.</p>
          </div>
        </div>
        <div className="shadow-md cursor-pointer grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 pt-12 p-6 ">
          <img src="pexels-saramazin-19884480.jpg" alt="pexels-saramazin-19884480.jpg" className="rounded-md" />
          <img src="pexels-saramazin-19884480.jpg" alt="pexels-saramazin-19884480.jpg" className="rounded-md" />
          <img src="pexels-saramazin-19884480.jpg" alt="pexels-saramazin-19884480.jpg" className="rounded-md" />
          <img src="pexels-saramazin-19884480.jpg" alt="pexels-saramazin-19884480.jpg" className="rounded-md" />
        </div>
      </div>
      <div className=" py-24 sm:py-32">
      <div className="mx-auto max-w-7xl px-6 lg:px-8">
        <dl className="grid grid-cols-1 gap-x-8 gap-y-16 text-center lg:grid-cols-3">
          {stats.map((stat) => (
            <div key={stat.id} className="mx-auto flex max-w-xs flex-col gap-y-4">
              <dt className="">{stat.name}</dt>
              <dd className="order-first text-3xl font-semibold tracking-tight  sm:text-5xl">{stat.value}</dd>
            </div>
          ))}
        </dl>
      </div>
    </div>
    <Contact/>
    </div>
  )
}

export default About