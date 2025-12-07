import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"

const Footer = () => {
  return (
    <div className="w-full bg-gray-800 text-white">
      <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-4 gap-4 sm:gap-6 md:gap-8 lg:gap-10 py-4 pdg">
        <div className="space-y-4">
          <h4 className="text-lg font-semibold">Solutions</h4>
          <ul className="space-y-4 text-sm">
            <li>Marketing</li>
            <li>Analytics</li>
            <li>Automation</li>
            <li>Commerce</li>
            <li>Insights</li>
          </ul>
        </div>
        <div className="space-y-4">
          <h4 className="text-lg font-semibold">Support</h4>
          <ul className="space-y-4">
            <li>Submit ticket</li>
            <li>Documentation</li>
            <li>Guides</li>
          </ul>
        </div>
        <div className="space-y-4">
          <h4 className="text-lg font-semibold">Company</h4>
          <ul className="space-y-4">
            <li>About</li>
            <li>Blog</li>
            <li>Job</li>
            <li>Press</li>
          </ul>
        </div>
        <div className="space-y-4">
          <h4 className="text-lg font-semibold">Legal</h4>
          <ul className="space-y-4">
            <li>Terms of service</li>
            <li>Privacy policy</li>
            <li>License</li>
          </ul>
        </div>
      </div>
      {/* footer bottom */}
      <div className="flex flex-col md:flex-row items-center justify-between  gap-4 sm:gap-6 md:gap-8 lg:gap-10 pdg">
        <div className="space-y-2.5">
          <h1 className="text-lg font-semibold">Subscribe to our newsletter</h1>
          <p className="text-sm">The latest news, articles, and resources, sent to your inbox weekly.</p>
        </div>
        <div className="flex flex-col md:flex-row items-center gap-3 w-full max-w-md">
          <Input type="text" placeholder="Enter your email" />
          <Button className="w-full md:w-32">Subscrice</Button>
        </div>
      </div>
    </div>
  )
}

export default Footer