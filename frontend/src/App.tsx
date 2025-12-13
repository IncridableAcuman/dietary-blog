import { AppRouter } from "./app/routes/routes"
import { useAuthStore } from "./app/store/auth/auth.store";
import "./app/styles/global.css"
import { Spinner } from "./components/ui/spinner";
const App = () => {
  const { isLoading } = useAuthStore();



  return (
    <>
      {isLoading && (
        <div className="fixed inset-0 bg-black/60 flex items-center justify-center z-9999">
          <Spinner className="text-white" width={32} />
        </div>
      )}
      <AppRouter />
    </>
  );
}

export default App