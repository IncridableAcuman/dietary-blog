import { useEffect } from "react";
import { AppRouter } from "./app/routes/routes"
import { useAuthStore } from "./app/store/auth/auth.store";
import "./app/styles/global.css"
import { Spinner } from "./components/ui/spinner";
import axiosInstance from "./shared/api/axiosInstance";
const App = () => {
  const { isLoading, setUser, clearAuth } = useAuthStore();

  useEffect(() => {
    const initAuth = async () => {
      const token = localStorage.getItem("accessToken");
      if (!token) return;

      try {
        const { data } = await axiosInstance.get("/auth/me");
        setUser(data);
      } catch {
        localStorage.removeItem("accessToken");
        clearAuth();
      }
    };

    initAuth();
  }, [clearAuth, setUser]);


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